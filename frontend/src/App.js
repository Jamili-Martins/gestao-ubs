import React from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";

import FormMedico from "./components/FormMedico";
import ListaMedicos from "./components/ListaMedicos";
import FormPaciente from "./components/FormPaciente";
import ListaPacientes from "./components/ListaPacientes";
import FormConsulta from "./components/FormConsulta";
import ListaConsultas from "./components/ListaConsultas";

function App() {
  return (
    <Router>
      <div>
        <h1>Meu Sistema</h1>
        <nav>
          <Link to="/medicos">Médicos</Link> |
          <Link to="/pacientes">Pacientes</Link> |
          <Link to="/consultas">Consultas</Link>
        </nav>

        <Routes>
          <Route path="/medicos" element={
            <>
              <FormMedico />
              <ListaMedicos />
            </>
          } />
          <Route path="/pacientes" element={
            <>
              <FormPaciente />
              <ListaPacientes />
            </>
          } />
          <Route path="/consultas" element={
            <>
              <FormConsulta />
              <ListaConsultas />
            </>
          } />
          <Route path="*" element={<h2>Bem-vindo ao Sistema!</h2>} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
