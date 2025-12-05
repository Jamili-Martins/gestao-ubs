import React, { useEffect, useState } from "react";
import api from "../services/api";

function ListaConsultas() {
  const [consultas, setConsultas] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      const response = await api.get("/consultas");
      setConsultas(response.data);
    };
    fetchData();
  }, []);

  return (
    <div>
      <h2>Lista de Consultas</h2>
      <ul>
        {consultas.map((c) => (
          <li key={c.id}>
            Paciente: {c.pacienteNome}, Médico: {c.medicoNome}, Data: {c.data}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default ListaConsultas;
