import { useEffect, useState } from "react";
import api from "../../services/api";

function ListSales() {
  const [sales, setSales] = useState([]);
  const [statusFilter, setStatusFilter] = useState("");

  useEffect(() => {
    loadSales();
  }, [statusFilter]);

  const loadSales = async () => {
    try {
      const allSales = await api("/sales");

      if (statusFilter) {
        setSales(allSales.filter((sale: any) => sale.saleStatus === statusFilter));
      } else {
        setSales(allSales);
      }
    } catch (err) {
      console.error("Erro ao carregar vendas", err);
    }
  };

  return (
    <div>
      <h2>Lista de Vendas</h2>

      <select onChange={(e) => setStatusFilter(e.target.value)} value={statusFilter}>
        <option value="">Todos os Status</option>
        <option value="EM_ABERTO">Em Aberto</option>
        <option value="CANCELADA">Cancelada</option>
        <option value="CONFIRMADA">Confirmada</option>
      </select>

      {sales.map((sale: any) => (
        <div key={sale.id} style={{ border: "1px solid #ccc", padding: 10, margin: 10 }}>
          <p><strong>Usuário:</strong> {sale.user?.name || sale.userId}</p>
          <p><strong>Evento:</strong> {sale.eventId?.description || sale.eventId}</p>
          <p><strong>Status:</strong> {sale.saleStatus}</p>
        </div>
      ))}
    </div>
  );
}

export default ListSales;
