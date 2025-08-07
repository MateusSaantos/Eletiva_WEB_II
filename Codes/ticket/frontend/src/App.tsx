import './App.css';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';

import ListUsers from './components/users/ListUsers';
import CreateEvent from './components/users/CreateEvent'; // ajuste se estiver em outro caminho
import ListSales from './components/users/ListSales';
import CreateSale from './components/users/CreateSale';
import UpdateSaleStatus from './components/users/UpdateSaleStatus';


function App() {
  return (
    <BrowserRouter>
      <h1>Sistema de Vendas de Tickets</h1>

      <nav>
        <Link to="/list-users">Usuários</Link> |{' '}
        <Link to="/create-event">Criar Evento</Link> |{' '}
        <Link to="/list-sales">Vendas</Link> |{' '}
        <Link to="/create-sale">Cadastrar Venda</Link> |{' '}
        <Link to="/update-sale">Alterar Status</Link> |{' '}

      </nav>

      <Routes>
        <Route path="/list-users" element={<ListUsers />} />
        <Route path="/create-event" element={<CreateEvent />} />
        <Route path="/list-sales" element={<ListSales />} />
        <Route path="/create-sale" element={<CreateSale />} />
        <Route path="/update-sale" element={<UpdateSaleStatus />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
