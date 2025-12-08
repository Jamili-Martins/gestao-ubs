const API = "http://localhost:8080/pacientes";
const tablePacientes = document.querySelector("#tablePacientes tbody");
const msgPacientes = document.getElementById("msgPacientes");
const modalPaciente = new bootstrap.Modal(document.getElementById("modalPaciente"));
const formPaciente = document.getElementById("formPaciente");
const pNome = document.getElementById("pNome"), pCpf = document.getElementById("pCpf"), pTelefone = document.getElementById("pTelefone"), pId = document.getElementById("pId");
const btnNovo = document.getElementById("btnNovo"), btnAtualizar = document.getElementById("btnAtualizar");

async function carregarPacientes(){
    tablePacientes.innerHTML = "<tr><td colspan='5'>Carregando...</td></tr>";
    try{
        const res = await fetch(API);
        if(!res.ok) throw new Error("Erro listar");
        const lista = await res.json();
        if(!Array.isArray(lista) || lista.length===0) return tablePacientes.innerHTML = "<tr><td colspan='5'>Nenhum paciente cadastrado.</td></tr>";
        tablePacientes.innerHTML = "";
        lista.forEach(p=>{
            const tr = document.createElement("tr");
            tr.innerHTML = `<td>${p.id||""}</td><td>${escapeHtml(p.nome||"")}</td><td>${escapeHtml(p.cpf||"")}</td><td>${escapeHtml(p.telefone||"")}</td>
        <td>
          <button class="btn btn-sm btn-outline-primary me-1" data-id="${p.id}" data-action="edit">✏️</button>
          <button class="btn btn-sm btn-outline-danger" data-id="${p.id}" data-action="del">🗑️</button>
        </td>`;
            tr.querySelector('[data-action="edit"]').addEventListener("click",()=>abrirEdicao(p));
            tr.querySelector('[data-action="del"]').addEventListener("click",()=>removerPacienteConfirm(p.id,p.nome));
            tablePacientes.appendChild(tr);
        })
    }catch(e){
        console.error(e);
        tablePacientes.innerHTML = "<tr><td colspan='5'>Erro ao carregar pacientes.</td></tr>";
    }
}

function abrirEdicao(p){
    pId.value = p.id; pNome.value = p.nome || ""; pCpf.value = p.cpf || ""; pTelefone.value = p.telefone || "";
    document.getElementById("modalTitle").innerText = `Editar Paciente (ID ${p.id})`;
    modalPaciente.show();
}

btnNovo.addEventListener("click", ()=>{
    pId.value=""; pNome.value=""; pCpf.value=""; pTelefone.value="";
    document.getElementById("modalTitle").innerText = "Novo Paciente";
    modalPaciente.show();
});
btnAtualizar.addEventListener("click", carregarPacientes);

formPaciente.addEventListener("submit", async (e)=>{
    e.preventDefault(); document.getElementById("formMsg").innerText = "";
    const payload = {nome: pNome.value.trim(), cpf: pCpf.value.trim(), telefone: pTelefone.value.trim()};
    try{
        if(pId.value){
            const res = await fetch(`${API}/${pId.value}`, {method:"PUT",headers:{"Content-Type":"application/json"},body:JSON.stringify(payload)});
            if(!res.ok) throw new Error("Erro ao atualizar");
        } else {
            const res = await fetch(API, {method:"POST",headers:{"Content-Type":"application/json"},body:JSON.stringify(payload)});
            if(!res.ok) throw new Error("Erro ao criar");
        }
        modalPaciente.hide();
        carregarPacientes();
    }catch(err){
        console.error(err);
        document.getElementById("formMsg").innerText = "Erro: "+err.message;
    }
});

function removerPacienteConfirm(id,nome){
    if(!confirm(`Remover paciente ${nome} (ID ${id})?`)) return;
    removerPaciente(id);
}
async function removerPaciente(id){
    try{
        const res = await fetch(`${API}/${id}`,{method:"DELETE"});
        if(!res.ok) throw new Error("Erro ao remover");
        carregarPacientes();
    }catch(e){ alert("Erro: "+e.message) }
}

function escapeHtml(s){return String(s).replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));}

carregarPacientes();