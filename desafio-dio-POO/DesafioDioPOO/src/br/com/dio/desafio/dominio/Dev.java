package br.com.dio.desafio.dominio;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Dev {

	private String nome;
	private Set<Conteudos> conteudosInscritos = new LinkedHashSet<>();
	private Set<Conteudos> conteudosConcluidos = new LinkedHashSet<>();

	
	public Dev() {
		
	}
	
	public Dev(String nome) {
		this.nome = nome;
	}
	
	public void inscreverBootcamp(BootCamp bootcamp) {
		
		this.conteudosInscritos.addAll(bootcamp.getConteudos());
		bootcamp.getDevInscritos().add(this);

	}

	public void progedir() {
		
	 Optional <Conteudos> conteudo = this.conteudosInscritos.stream().findFirst();
	 if(conteudo.isPresent()) {
		 this.conteudosConcluidos.add(conteudo.get());
		 this.conteudosInscritos.remove(conteudo.get());
	 } else {
		 System.err.println("Você não está matriculado em nenhum conteúdo!");
	 }
	}

	public double calcularXp() {
		// outra opção this.conteudosConcluidos.stream().mapToDouble(Conteudo::calcularXP).sum();
		return this.conteudosConcluidos.stream().mapToDouble(conteudo -> conteudo.calcularXP()).sum();
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Conteudos> getConteudosInscritos() {
		return conteudosInscritos;
	}

	public void setConteudosInscritos(Set<Conteudos> conteudosInscritos) {
		this.conteudosInscritos = conteudosInscritos;
	}

	public Set<Conteudos> getConteudosConcluidos() {
		return conteudosConcluidos;
	}

	public void setConteudosConcluidos(Set<Conteudos> conteudosConcluidos) {
		this.conteudosConcluidos = conteudosConcluidos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(conteudosConcluidos, conteudosInscritos, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dev other = (Dev) obj;
		return Objects.equals(conteudosConcluidos, other.conteudosConcluidos)
				&& Objects.equals(conteudosInscritos, other.conteudosInscritos) && Objects.equals(nome, other.nome);
	}

}
