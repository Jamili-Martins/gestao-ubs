const API_BASE = "http://localhost:8080";

const reportType = document.getElementById("reportType");
const medicosSelect = document.getElementById("medicosSelect");
const medicoField = document.getElementById("medicoField");
const dataEspecifica = document.getElementById("dataEspecifica");
const dataEspecificaField = document.getElementById("dataEspecificaField");
const dataInicio = document.getElementById("dataInicio");
const dataFim = document.getElementById("dataFim");
const inicioField = document.getElementById("inicioField");
const fimField = document.getElementById("fimField");
const gerarBtn = document.getElementById("gerarBtn");
const limparBtn = document.getElementById("limparBtn");
const messageEl = document.getElementById("message");
const tableArea = document.getElementById("table-area");
const chartArea = document.getElementById("chart-area");

let currentChart = null;

function showMessage(msg, error = true) {
    messageEl.style.color = error ? "red" : "green";
    messageEl.innerText = msg;
}

function clearMessage() { messageEl.innerText = ""; }

function escapeHtml(s) {
    return String(s || "")
        .replace(/[&<>"']/g, c => ({
            "&": "&amp;",
            "<": "&lt;",
            ">": "&gt;",
            "\"": "&quot;",
            "'": "&#39;"
        }[c]));
}

function toggleFields() {
    const t = reportType.value;

    medicoField.classList.add("d-none");
    dataEspecificaField.classList.add("d-none");
    inicioField.classList.add("d-none");
    fimField.classList.add("d-none");

    if (t === "por-medico") {
        medicoField.classList.remove("d-none");
        dataEspecificaField.classList.remove("d-none");
    }
    else if (t === "por-data") {
        dataEspecificaField.classList.remove("d-none");
    }
    else if (t === "por-intervalo") {
        inicioField.classList.remove("d-none");
        fimField.classList.remove("d-none");
    }
}

async function carregarMedicos() {
    try {
        const res = await fetch(`${API_BASE}/medicos`);
        const lista = res.ok ? await res.json() : [];

        medicosSelect.innerHTML =
            `<option value="">-- selecione --</option>` +
            lista.map(m => `<option value="${m.id}">${m.id} — ${escapeHtml(m.nome)}</option>`).join("");

    } catch (e) {
        showMessage("Erro ao carregar médicos.");
    }
}

function criarURLRelatorio() {
    const tipo = reportType.value;

    if (tipo === "por-medico") {
        if (!medicosSelect.value) return null;

        let url = `${API_BASE}/relatorios/consultas/medico/${medicosSelect.value}`;

        if (dataEspecifica.value)
            url += `?data=${dataEspecifica.value}`;

        return url;
    }

    if (tipo === "por-data") {
        if (!dataEspecifica.value) return null;
        return `${API_BASE}/relatorios/consultas/data?data=${dataEspecifica.value}`;
    }

    if (tipo === "por-intervalo") {
        if (!dataInicio.value || !dataFim.value) return null;

        return `${API_BASE}/relatorios/consultas/data?dataInicio=${dataInicio.value}&dataFim=${dataFim.value}`;
    }

    return null;
}

async function gerarRelatorio() {
    clearMessage();
    tableArea.innerHTML = "";

    const url = criarURLRelatorio();
    if (!url) {
        showMessage("Preencha corretamente os campos.");
        return;
    }

    try {
        showMessage("Carregando...", false);

        const res = await fetch(url);
        if (!res.ok) throw new Error("Erro no backend");

        const lista = await res.json();

        clearMessage();
        renderResultados(lista);

    } catch (e) {
        showMessage("Erro ao gerar relatório.");
    }
}

function renderResultados(lista) {
    if (!lista.length) {
        tableArea.innerHTML = `<div class="report-card">Nenhum registro encontrado.</div>`;
        return;
    }

    const rows = lista.map(c => `
        <tr>
            <td>${c.id}</td>
            <td>${escapeHtml(c.paciente?.nome)}</td>
            <td>${escapeHtml(c.medico?.nome)}</td>
            <td>${c.dataHora.replace("T", " ")}</td>
            <td>${escapeHtml(c.observacoes)}</td>
        </tr>
    `).join("");

    tableArea.innerHTML = `
        <div class="report-card">
            <table class="table table-striped">
                <thead>
                    <tr><th>ID</th><th>Paciente</th><th>Médico</th><th>Data/Hora</th><th>Obs</th></tr>
                </thead>
                <tbody>${rows}</tbody>
            </table>
        </div>
    `;

    gerarGrafico(lista);
}

function gerarGrafico(lista) {
    const agrupamento = {};

    lista.forEach(c => {
        const d = c.dataHora.split("T")[0];
        agrupamento[d] = (agrupamento[d] || 0) + 1;
    });

    const labels = Object.keys(agrupamento).sort();
    const data = labels.map(k => agrupamento[k]);

    chartArea.classList.remove("d-none");

    if (currentChart) currentChart.destroy();

    currentChart = new Chart(document.getElementById("reportChart"), {
        type: "bar",
        data: {
            labels,
            datasets: [{
                label: "Consultas",
                data,
                backgroundColor: "rgba(0,123,255,.6)"
            }]
        }
    });
}

document.addEventListener("DOMContentLoaded", () => {
    toggleFields();
    reportType.addEventListener("change", toggleFields);
    gerarBtn.addEventListener("click", gerarRelatorio);

    limparBtn.addEventListener("click", () => {
        dataEspecifica.value = "";
        dataInicio.value = "";
        dataFim.value = "";
        medicosSelect.value = "";
        tableArea.innerHTML = "";
        chartArea.classList.add("d-none");
        clearMessage();
    });

    carregarMedicos();
});