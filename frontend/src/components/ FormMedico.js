import React, { useState } from "react";
import FormMedico from "./components/FormMedico";


function FormMedico() {
  const [nome, setNome] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.post("/medicos", { nome });
      setNome("");
      alert("Médico cadastrado!");
    } catch (error) {
      console.error("Erro ao cadastrar médico", error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Cadastrar Médico</h2>
      <input
        type="text"
        placeholder="Nome"
        value={nome}
        onChange={(e) => setNome(e.target.value)}
        required
      />
      <button type="submit">Cadastrar</button>
    </form>
  );
}

export default FormMedico;
