const API_CON = "http://localhost:8080/consultas";
const API_PAC = "http://localhost:8080/pacientes";
const API_MED = "http://localhost:8080/medicos";

const tableConsultas = document.querySelector("#tableConsultas tbody");
const modalConsulta = new bootstrap.Modal(document.getElementById("modalConsulta"));
const formConsulta = document.getElementById("formConsulta");
const cPaciente = document.getElementById("cPaciente"), cMedico = document.getElementById("cMedico"), cDataHora = document.getElementById("cDataHora"), cObs = document.getElementById("cObs"), cId = document.getElementById("cId");

document.getElementById("btnNovaConsulta").addEventListener("click", async ()=>{
    await popularSelects(); cId.value=""; cDataHora.value=""; cObs.value=""; document.getElementById("modalConsultaTitle").innerText="Nova Consulta"; modalConsulta.show();
});
document.getElementById("btnRefreshConsulta").addEventListener("click", carregarConsultas);

async function popularSelects(){
    try{
        const [pRes,mRes] = await Promise.all([fetch(API_PAC),fetch(API_MED)]);
        const pac = pRes.ok ? await pRes.json() : [];
        const med = mRes.ok ? await mRes.json() : [];
        cPaciente.innerHTML = pac.map(p=>`<option value="${p.id}">${p.id} - ${p.nome||p.cpf||"Paciente"}</option>`).join("");
        cMedico.innerHTML = med.map(m=>`<option value="${m.id}">${m.id} - ${m.nome||m.crm||"Médico"}</option>`).join("");
    }catch(e){ console.error(e); alert("Erro carregar pacientes/medicos"); }
}

async function carregarConsultas(){
    tableConsultas.innerHTML = "<tr><td colspan='6'>Carregando...</td></tr>";
    try{
        const res = await fetch(API_CON);
        if(!res.ok) throw new Error("Erro listar consultas");
        const lista = await res.json();
        if(!Array.isArray(lista) || lista.length===0) return tableConsultas.innerHTML="<tr><td colspan='6'>Nenhuma consulta.</td></tr>";
        tableConsultas.innerHTML="";
        lista.forEach(c=>{
            const pNome = c.paciente?.nome || `#${c.paciente?.id||""}`;
            const mNome = c.medico?.nome || `#${c.medico?.id||""}`;
            const tr = document.createElement("tr");
            tr.innerHTML = `<td>${c.id||""}</td><td>${escapeHtml(pNome)}</td><td>${escapeHtml(mNome)}</td><td>${escapeHtml(c.dataHora||"")}</td><td>${escapeHtml(c.observacoes||"")}</td>
      <td><button class="btn btn-sm btn-outline-primary me-1" data-id="${c.id}" data-action="edit">✏️</button><button class="btn btn-sm btn-outline-danger" data-id="${c.id}" data-action="del">🗑️</button></td>`;
            tr.querySelector('[data-action="edit"]').addEventListener("click",()=>abrirEditarConsulta(c));
            tr.querySelector('[data-action="del"]').addEventListener("click",()=>removerConfirm(c.id));
            tableConsultas.appendChild(tr);
        })
    }catch(e){ console.error(e); tableConsultas.innerHTML="<tr><td colspan='6'>Erro ao carregar consultas.</td></tr>"; }
}

function abrirEditarConsulta(c){
    popularSelects().then(() => {
        setTimeout(() => {
            cId.value = c.id;
            cPaciente.value = c.paciente?.id;
            cMedico.value = c.medico?.id;
        }, 80); // tempo suficiente p/ renderizar

        const dt = new Date(c.dataHora);
        const local = new Date(dt - dt.getTimezoneOffset()*60000).toISOString().slice(0,16);
        cDataHora.value = local;

        cObs.value = c.observacoes || "";
        document.getElementById("modalConsultaTitle").innerText = `Editar Consulta (ID ${c.id})`;
        modalConsulta.show();
    });
}

formConsulta.addEventListener("submit", async (e)=>{
    e.preventDefault();
    const payload = {
        paciente: { id: Number(cPaciente.value) },
        medico: { id: Number(cMedico.value) },
        dataHora: new Date(cDataHora.value).toISOString(),
        observacoes: cObs.value || ""
    };
    try{
        if(cId.value){
            const res = await fetch(`${API_CON}/${cId.value}`, {method:"PUT", headers:{"Content-Type":"application/json"}, body: JSON.stringify(payload)});
            if(!res.ok){
                const err = await res.json().catch(()=>null);
                throw new Error(err?.message || "Erro atualizar");
            }
        } else {
            const res = await fetch(API_CON, {method:"POST", headers:{"Content-Type":"application/json"}, body: JSON.stringify(payload)});
            if(!res.ok){
                const err = await res.json().catch(()=>null);
                throw new Error(err?.message || "Erro criar");
            }
        }
        modalConsulta.hide(); carregarConsultas();
    }catch(err){ document.getElementById("formConsultaMsg").innerText = "Erro: "+err.message; console.error(err); }
});

function removerConfirm(id){ if(!confirm(`Remover consulta ID ${id}?`)) return; removerConsulta(id); }
async function removerConsulta(id){ try{ const res = await fetch(`${API_CON}/${id}`,{method:"DELETE"}); if(!res.ok) throw new Error("Erro remover"); carregarConsultas(); }catch(e){ alert("Erro: "+e.message); } }

function escapeHtml(s){return String(s).replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));}

carregarConsultas();