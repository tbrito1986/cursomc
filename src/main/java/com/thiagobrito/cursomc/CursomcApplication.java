package com.thiagobrito.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.thiagobrito.cursomc.domain.Categoria;
import com.thiagobrito.cursomc.domain.Cidade;
import com.thiagobrito.cursomc.domain.Cliente;
import com.thiagobrito.cursomc.domain.Endereco;
import com.thiagobrito.cursomc.domain.Estado;
import com.thiagobrito.cursomc.domain.ItemPedido;
import com.thiagobrito.cursomc.domain.Pagamento;
import com.thiagobrito.cursomc.domain.PagamentoComBoleto;
import com.thiagobrito.cursomc.domain.PagamentoComCartao;
import com.thiagobrito.cursomc.domain.Pedido;
import com.thiagobrito.cursomc.domain.Produto;
import com.thiagobrito.cursomc.domain.enums.EstadoPagamento;
import com.thiagobrito.cursomc.domain.enums.TipoCliente;
import com.thiagobrito.cursomc.repositories.CategoriaRepository;
import com.thiagobrito.cursomc.repositories.CidadeRepository;
import com.thiagobrito.cursomc.repositories.ClienteRepository;
import com.thiagobrito.cursomc.repositories.EnderecoRepository;
import com.thiagobrito.cursomc.repositories.EstadoRepository;
import com.thiagobrito.cursomc.repositories.ItemPedidoRepository;
import com.thiagobrito.cursomc.repositories.PagamentoRepository;
import com.thiagobrito.cursomc.repositories.PedidoRepository;
import com.thiagobrito.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 300.00);
		Produto p3 = new Produto(null, "Mouse", 30.00);
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "2423424221342", TipoCliente.PESSOAFISICA);
		
		Endereco e1 = new Endereco(null, "Rua Floreas", "400", "Apto 303", "Jardim", "232132131", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Santa Maria", "77", "Casa", "Vila Santa Terezinha", "13131", cli1, c2);
		
		SimpleDateFormat stf = new SimpleDateFormat("dd/mm/yyyy HH:MM");
		
		Pedido ped1 = new Pedido(null, stf.parse("08/08/2019 10:23"), cli1, e1);
		Pedido ped2 = new Pedido(null, stf.parse("07/07/2019 10:34"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, stf.parse("20/10/2019 00:00"), null);
		ped2.setPagamento(pagto2);
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.0, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.0, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.0, 1, 800.00);
		
		ped1.getItem().addAll(Arrays.asList(ip1, ip2));
		ped2.getItem().addAll(Arrays.asList(ip3));
		
		p1.getItem().addAll(Arrays.asList(ip1));
		p2.getItem().addAll(Arrays.asList(ip3));
		p3.getItem().addAll(Arrays.asList(ip2));
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		cli1.getTelefones().addAll(Arrays.asList("2131231", "34234234"));
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}

}
