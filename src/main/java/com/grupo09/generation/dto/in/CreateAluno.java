package com.grupo09.generation.dto.in;

import java.util.Optional;

public record CreateAluno(String nome,String email,int idade,Optional<Double> notaPrimeiroModulo,Optional<Double> notaSegundoModulo,
                          Optional<Double> media){
}
