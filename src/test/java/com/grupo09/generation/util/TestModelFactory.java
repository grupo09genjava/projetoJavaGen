package com.grupo09.generation.util;
import com.grupo09.generation.model.AlunoModel;
import com.grupo09.generation.model.TurmaModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestModelFactory{
    public static TurmaModel createTurma(Long id, String nome, String instrutor, List<AlunoModel> alunos) {
        return TurmaModel.builder()
                .id(id)
                .nome(nome)
                .instrutor(instrutor)
                .alunos(alunos)
                .build();
    }

    public static AlunoModel createAluno(Long id, String nome, String email, int idade,
                                         double notaPrimeiroModulo, double notaSegundoModulo,
                                         double media, TurmaModel turma) {
        return AlunoModel.builder()
                .id(id)
                .nome(nome)
                .email(email)
                .idade(idade)
                .notaPrimeiroModulo(notaPrimeiroModulo)
                .notaSegundoModulo(notaSegundoModulo)
                .media(media)
                .turma(turma)
                .build();
    }

    public static AlunoModel createDefaultAluno(Long id, String nome, TurmaModel turma) {
        var email = nome + "@mail.com";
        return createAluno(id, nome, email , 18, 7.0, 7.5, 7.25, turma);
    }

    public static TurmaModel createTurmaWithDefaultAlunos() {
        TurmaModel turma = createTurma(1L, "Turma 1", "Joselito", Collections.emptyList());
        AlunoModel aluno1 = createDefaultAluno(1L, "João", turma);
        AlunoModel aluno2 = createDefaultAluno(2L, "Maria", turma);
        turma.setAlunos(Arrays.asList(aluno1, aluno2));
        return turma;
    }


    public static TurmaModel createEmptyTurma() {
        return createTurma(2L, "Turma 2", "Cloudio", Collections.emptyList());
    }

}
