package desafio_prothera;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Locale;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }
    public String getFuncao() {
        return funcao;
    }
     
    
    public BigDecimal calcularSalarioMinimo() {
        BigDecimal salarioMinimo = BigDecimal.valueOf(1212.00);
        return salario.divide(salarioMinimo,2, RoundingMode.HALF_UP);
    }
    
    public void aumentarSalario(BigDecimal percentualAumento) {
        BigDecimal aumento = salario.multiply(percentualAumento.divide(BigDecimal.valueOf(100)));
        this.salario = salario.add(aumento);
    }
    
    @Override
    public String toString() {
    	Locale locale = new Locale("pt", "BR");
    	return super.toString() + String.format(locale, ", Salário:R$ %,.2f, Função: %s", salario, funcao);
    }
}
