const API_MED = "http://localhost:8080/medicos";
const tableMedicos = document.querySelector("#tableMedicos tbody");
const modalMedico = new bootstrap.Modal(document.getElementById("modalMedico"));
const formMedico = document.getElementById("formMedico");
const mNome = document.getElementById("mNome"), mEsp = document.getElementById("mEspecialidade"), mCrm = document.getElementById("mCrm"), mId = document.getElementById("mId");
document.getElementById("btnNovoMed").addEventListener("click", ()=>{ mId.value=""; mNome.value=""; mEsp.value=""; mCrm.value=""; document.getElementById("modalMedTitle").innerText="Novo Médico"; modalMedico.show();});
document.getElementById("btnRefreshMed").addEventListener("click", carregarMedicos);

async function carregarMedicos(){
    tableMedicos.innerHTML = "<tr><td colspan='5'>Carregando...</td></tr>";
    try{
        const res = await fetch(API_MED);
        if(!res.ok) throw new Error("Erro listar médicos");
        const lista = await res.json();
        if(!Array.isArray(lista) || lista.length===0) return tableMedicos.innerHTML = "<tr><td colspan='5'>Nenhum médico cadastrado.</td></tr>";
        tableMedicos.innerHTML="";
        lista.forEach(m=>{
            const tr=document.createElement("tr");
            tr.innerHTML=`<td>${m.id||""}</td><td>${escapeHtml(m.nome||"")}</td><td>${escapeHtml(m.especialidade||"")}</td><td>${escapeHtml(m.crm||"")}</td>
      <td><button class="btn btn-sm btn-outline-primary me-1" data-id="${m.id}" data-action="edit">✏️</button>
      <button class="btn btn-sm btn-outline-danger" data-id="${m.id}" data-action="del">🗑️</button></td>`;
            tr.querySelector('[data-action="edit"]').addEventListener("click",()=>{ mId.value=m.id; mNome.value=m.nome||""; mEsp.value=m.especialidade||""; mCrm.value=m.crm||""; document.getElementById("modalMedTitle").innerText=`Editar Médico (ID ${m.id})`; modalMedico.show();});
            tr.querySelector('[data-action="del"]').addEventListener("click",()=>removerMedicoConfirm(m.id,m.nome));
            tableMedicos.appendChild(tr);
        });
    }catch(e){ console.error(e); tableMedicos.innerHTML="<tr><td colspan='5'>Erro ao carregar médicos.</td></tr>"; }
}

formMedico.addEventListener("submit", async (ev)=>{
    ev.preventDefault();
    const payload = {nome:mNome.value.trim(), especialidade:mEsp.value.trim(), crm:mCrm.value.trim()};
    try{
        if(mId.value){
            const res = await fetch(`${API_MED}/${mId.value}`,{method:"PUT",headers:{"Content-Type":"application/json"},body:JSON.stringify(payload)});
            if(!res.ok) throw new Error("Erro atualizar");
        } else {
            const res = await fetch(API_MED,{method:"POST",headers:{"Content-Type":"application/json"},body:JSON.stringify(payload)});
            if(!res.ok) throw new Error("Erro criar");
        }
        modalMedico.hide(); carregarMedicos();
    }catch(err){ alert("Erro: "+err.message) }
});

function removerMedicoConfirm(id,nome){ if(!confirm(`Remover médico ${nome} (ID ${id})?`)) return; removerMedico(id); }
async function removerMedico(id){ try{ const res=await fetch(`${API_MED}/${id}`,{method:"DELETE"}); if(!res.ok) throw new Error("Erro remover"); carregarMedicos(); }catch(e){ alert("Erro: "+e.message) } }

function escapeHtml(s){return String(s).replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));}

carregarMedicos();