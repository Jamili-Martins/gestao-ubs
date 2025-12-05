import React, { useEffect, useState } from "react";
import api from "../services/api";

function ListaMedicos() {
  const [medicos, setMedicos] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      const response = await api.get("/medicos");
      setMedicos(response.data);
    };
    fetchData();
  }, []);

  return (
    <div>
      <h2>Lista de Médicos</h2>
      <ul>
        {medicos.map((m) => <li key={m.id}>{m.nome}</li>)}
      </ul>
    </div>
  );
}

export default ListaMedicos;
