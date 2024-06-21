package desafio_prothera;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {

    public static void main(String[] args) {
        
    	/* 3.1 Inseririndo funcionarios */
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("Joao", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloisa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
        
        
        /* 3.2 Remover o funcionário "Joao" da lista independente de esta maiusculo ou minusculo*/
        funcionarios.removeIf(funcionario -> funcionario.getNome().equalsIgnoreCase("Joao"));

        /* 3.3 Imprimir todos os funcionários */
        System.out.println("Funcionários:");
        funcionarios.forEach(System.out::println);

        /* 3.4 Enviando percentual de aumento de salário em 10% */
        funcionarios.forEach(funcionario -> funcionario.aumentarSalario(new BigDecimal("10.00")));

        /* 3.5 Agrupar funcionários por função em um MAP */
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        /* 3.6 Imprimir funcionários agrupados por função */
        System.out.println("\nFuncionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(System.out::println);
            System.out.println(); 
        });

        /* 3.7 Imprimir funcionários que fazem aniversário nos meses 10 e 12 */
        System.out.println("\nFuncionários que fazem aniversário em outubro e dezembro:");
        funcionarios.stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == 10 ||
                                       funcionario.getDataNascimento().getMonthValue() == 12)
                .forEach(System.out::println);

        /* 3.8 Encontrar funcionário com maior idade */
        Funcionario maisVelho = funcionarios.stream()
                .min((f1, f2) -> f1.getDataNascimento().compareTo(f2.getDataNascimento()))
                .orElseThrow(); // supondo que a lista nunca estará vazia

        int idadeMaisVelho = LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear();
        System.out.println("\nFuncionário mais velho:");
        System.out.println("Nome: " + maisVelho.getNome() + ", Idade: " + idadeMaisVelho);

        /* 3.9 Ordenar funcionários por ordem alfabética */
        System.out.println("\nFuncionários em ordem alfabética:");
        funcionarios.stream()
                .sorted((f1, f2) -> f1.getNome().compareTo(f2.getNome()))
                .forEach(System.out::println);

        /* 3.10 Calcular e imprimir total dos salários dos funcionários */
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários dos funcionários: R$ " + String.format(Locale.forLanguageTag("pt-BR"), "%,.2f", totalSalarios));

        /* 3.11 Quantidade de salários mínimos cada funcionário recebe */
        System.out.println("\nQuantidade de salário mínimo por pessoa:");
        funcionarios.forEach(funcionario ->
                System.out.println(funcionario.getNome() + " ganha " + funcionario.calcularSalarioMinimo() + " salários mínimos"));
        
    }
}
