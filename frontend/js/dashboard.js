const API_BASE = "http://localhost:8080";

async function carregarDashboard(){
    try{
        const [pacRes, medRes, conRes] = await Promise.all([
            fetch(`${API_BASE}/pacientes`),
            fetch(`${API_BASE}/medicos`),
            fetch(`${API_BASE}/consultas`)
        ]);
        const pacientes = pacRes.ok ? await pacRes.json() : [];
        const medicos = medRes.ok ? await medRes.json() : [];
        const consultas = conRes.ok ? await conRes.json() : [];

        document.getElementById("totalPacientes").innerText = Array.isArray(pacientes) ? pacientes.length : 0;
        document.getElementById("totalMedicos").innerText = Array.isArray(medicos) ? medicos.length : 0;
        document.getElementById("totalConsultas").innerText = Array.isArray(consultas) ? consultas.length : 0;

        const hoje = new Date().toISOString().split("T")[0];
        const hojeConsultas = (Array.isArray(consultas) ? consultas : []).filter(c => c.dataHora && c.dataHora.startsWith(hoje));

        const msg = document.getElementById("consultasHojeMsg");
        if(!hojeConsultas.length){
            msg.innerText = "Nenhuma consulta hoje.";
            return;
        }
        msg.innerHTML = "<ul>" + hojeConsultas.map(c => `<li>${(c.paciente && c.paciente.nome) || "Paciente desconhecido"} — ${c.dataHora.substring(11,16)}</li>`).join("") + "</ul>";
    }catch(err){
        console.error(err);
        document.getElementById("consultasHojeMsg").innerText = "Erro ao carregar dados.";
    }
}

carregarDashboard();