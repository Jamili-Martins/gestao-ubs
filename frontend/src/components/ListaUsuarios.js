import React, { useEffect, useState } from "react";
import api from "../services/api"; // ajusta o caminho para services/api.js

function ListaUsuarios() {
  const [usuarios, setUsuarios] = useState([]);
  const [erro, setErro] = useState("");

  useEffect(() => {
    api.get("/usuarios") // endpoint do backend que retorna os usuários
      .then((res) => {
        setUsuarios(res.data);
        setErro("");
      })
      .catch((err) => {
        console.log("Erro ao buscar usuários:", err);
        setErro("Erro ao buscar usuários");
      });
  }, []);

  return (
    <div style={{ padding: "20px", fontFamily: "Arial, sans-serif" }}>
      <h2>Lista de Usuários</h2>
      {erro && <p style={{ color: "red" }}>{erro}</p>}
      <ul>
        {usuarios.map((u) => (
          <li key={u.id}>{u.nome}</li>
        ))}
      </ul>
    </div>
  );
}

export default ListaUsuarios;
