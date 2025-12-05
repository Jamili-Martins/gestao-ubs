import React, { useState } from "react";
import api from "../services/api";

function FormUsuario() {
  const [nome, setNome] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.post("/usuarios", { nome });
      setNome("");
      alert("Usuário cadastrado!");
    } catch (error) {
      console.error("Erro ao cadastrar usuário", error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Cadastrar Usuário</h2>
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

export default FormUsuario;
