import React, { useEffect, useState } from "react";
import api from "../services/api";

function ListaPacientes() {
  const [pacientes, setPacientes] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      const response = await api.get("/pacientes");
      setPacientes(response.data);
    };
    fetchData();
  }, []);

  return (
    <div>
      <h2>Lista de Pacientes</h2>
      <ul>
        {pacientes.map((p) => <li key={p.id}>{p.nome}</li>)}
      </ul>
    </div>
  );
}

export default ListaPacientes;
