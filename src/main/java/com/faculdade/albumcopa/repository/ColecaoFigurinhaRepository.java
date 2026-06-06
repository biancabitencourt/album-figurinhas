package com.faculdade.albumcopa.repository;

import com.faculdade.albumcopa.model.ColecaoFigurinha;
import com.faculdade.albumcopa.model.Usuario;
import com.faculdade.albumcopa.model.Figurinha; 
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ColecaoFigurinhaRepository extends JpaRepository<ColecaoFigurinha, Long>	{
	List<ColecaoFigurinha> findByUsuario(Usuario usuario);
	Optional<ColecaoFigurinha> findByUsuarioAndFigurinha(Usuario usuario, Figurinha figurinha);
}
