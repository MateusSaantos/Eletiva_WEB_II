import { useEffect, useState } from "react";
import api from "../../services/api";

function UpdateSaleStatus() {
  const [sales, setSales] = useState([]);
  const [selectedSaleId, setSelectedSaleId] = useState("");
  const [newStatus, setNewStatus] = useState("PAGO");

  useEffect(() => {
    loadSales();
  }, []);

  const loadSales = async () => {
    try {
      const data = await api("/sales");
      setSales(data);
    } catch (err) {
      console.error("Erro ao carregar vendas", err);
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    const body = {
      id: selectedSaleId,
      saleStatus: newStatus,
    };

    console.log("Atualizando venda:", body);

    try {
      const response = await api("/sales/saleStatus", {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(body),
      });

      console.log("Resposta da API:", response);
      alert("Status da venda atualizado com sucesso!");
      loadSales(); // atualiza a lista
    } catch (err) {
      console.error("Erro ao atualizar venda", err);
      alert("Erro ao atualizar venda");
    }
  };

  return (
    <div>
      <h2>Atualizar Status de Venda</h2>

      <form onSubmit={handleSubmit}>
        <select
          value={selectedSaleId}
          onChange={(e) => setSelectedSaleId(e.target.value)}
          required
        >
          <option value="">Selecione a venda</option>
          {sales.map((sale: any) => (
            <option key={sale.id} value={sale.id}>
              {sale.user?.name || sale.userId} - {sale.eventId?.description || sale.eventId} (
              {sale.saleStatus})
            </option>
          ))}
        </select>

        <select value={newStatus} onChange={(e) => setNewStatus(e.target.value)}>
          <option value="EM_ABERTO">Em Aberto</option>
          <option value="PAGO">Pago</option>
          <option value="CANCELADO">Cancelado</option>
          <option value="ESTORNADO">Estornado</option>
        </select>

        <button type="submit">Atualizar Status</button>
      </form>
    </div>
  );
}

export default UpdateSaleStatus;
