package com.farmacia.core.modelo;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "compras")
public class Compra 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fecha_compra")
    private LocalDate fecha_compra;
    @Column(name = "hora_compra")
    private LocalTime hora_compra;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;
    @Column(name = "total")
    private BigDecimal total;
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompraProducto> comprasProductos = new ArrayList<>();
    //Constructores, getters y setters
    public Compra()
    {
    	
    }
	public Compra(Sucursal sucursal)
	{
		super();
		LocalDate fechaCompra = LocalDate.now();
		this.fecha_compra = fechaCompra;
		LocalTime hora_compra = LocalTime.now(ZoneId.of("America/New_York")).withSecond(0);;
		this.hora_compra = hora_compra;
		this.sucursal = sucursal;
		this.total = BigDecimal.ZERO;
		this.comprasProductos = new ArrayList<>();
	}
	public Long getId() 
	{
		return id;
	}
	public void setId(Long id) 
	{
		this.id = id;
	}
	public LocalDate getFecha_compra() 
	{
		return fecha_compra;
	}
	public void setFecha_compra(LocalDate fecha_compra) 
	{
		this.fecha_compra = fecha_compra;
	}
	public LocalTime getHora_compra() 
	{
		return hora_compra;
	}
	public void setHora_compra(LocalTime hora_compra) 
	{
		this.hora_compra = hora_compra;
	}
	public Sucursal getSucursal() 
	{
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) 
	{
		this.sucursal = sucursal;
	}
	public BigDecimal getTotal() 
	{
		return total;
	}
	public void setTotal(BigDecimal total) 
	{
		this.total = total;
	}
	public List<CompraProducto> getComprasProductos() 
	{
		return comprasProductos;
	}
	public void setComprasProductos(List<CompraProducto> comprasProductos) 
	{
		this.comprasProductos = comprasProductos;
	}
}