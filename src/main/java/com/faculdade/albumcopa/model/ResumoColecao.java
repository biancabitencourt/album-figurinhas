package com.faculdade.albumcopa.model;

import java.util.List;

public record ResumoColecao(

	List<ColecaoFigurinha>colecao,
	List<Integer> faltantes,
	List<ColecaoFigurinha> repetidas,
	
	int possuidas,
	int total,
	int progresso
)	
{}
