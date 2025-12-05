import React, { useState } from "react";
import api from "../services/api";

function FormPaciente() {
  const [nome, setNome] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.post("/pacientes", { nome });
      setNome("");
      alert("Paciente cadastrado!");
    } catch (error) {
      console.error("Erro ao cadastrar paciente", error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Cadastrar Paciente</h2>
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

export default FormPaciente;
