package com.tiendagenerica.tienda.Utilidades;

import org.modelmapper.ModelMapper;

/*
 * Esta clase nos permitira mapear las implementaciones
 * Nos permitira hacer conversiones para pasarlas a las respectivas
 * operaciones que necesitemos
 * 
 * IMPORTANTE: Esta clase requiere la agregacion de la dependencia modelmapper
 * en el pom.xml
 */
public class MHelpers {

	//importamos ModelMapper
	public static ModelMapper modelMapper() {
		//retornamos un nuevo objeto tipo modelMapper
		return new ModelMapper();
	}
}
