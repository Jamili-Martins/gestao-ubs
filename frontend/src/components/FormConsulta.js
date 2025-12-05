import React, { useState, useEffect } from "react";
import api from "../services/api";

function FormConsulta() {
  const [pacientes, setPacientes] = useState([]);
  const [medicos, setMedicos] = useState([]);
  const [pacienteId, setPacienteId] = useState("");
  const [medicoId, setMedicoId] = useState("");
  const [data, setData] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      const pac = await api.get("/pacientes");
      const med = await api.get("/medicos");
      setPacientes(pac.data);
      setMedicos(med.data);
    };
    fetchData();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.post("/consultas", { pacienteId, medicoId, data });
      setPacienteId("");
      setMedicoId("");
      setData("");
      alert("Consulta cadastrada!");
    } catch (error) {
      console.error("Erro ao cadastrar consulta", error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Cadastrar Consulta</h2>

      <select value={pacienteId} onChange={(e) => setPacienteId(e.target.value)} required>
        <option value="">Selecione o paciente</option>
        {pacientes.map((p) => (
          <option key={p.id} value={p.id}>{p.nome}</option>
        ))}
      </select>

      <select value={medicoId} onChange={(e) => setMedicoId(e.target.value)} required>
        <option value="">Selecione o médico</option>
        {medicos.map((m) => (
          <option key={m.id} value={m.id}>{m.nome}</option>
        ))}
      </select>

      <input
        type="datetime-local"
        value={data}
        onChange={(e) => setData(e.target.value)}
        required
      />

      <button type="submit">Cadastrar</button>
    </form>
  );
}

export default FormConsulta;
