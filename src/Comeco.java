import java.util.*;

public class Comeco {
    public static void main(String[] args) throws Exception {

        Scanner entrada = new Scanner(System.in);

        // ENTRADA DO CONJUNTO DE ESTADOS ------------------------------------------------
        HashSet<String> estados = new HashSet<>();

        String entradaEstados = entrada.nextLine();

        for (int i = 0; i < entradaEstados.length(); i++) {

            // Se o caractere for um espaco em branco, ele nao e adicionado no conjunto de
            // estados
            if (!Character.isWhitespace(entradaEstados.charAt(i))) {
                estados.add(entradaEstados);
            }
        }

        // System.out.println("Estados lidos: " + estados);

        // ENTRADA DO ALFABETO ----------------------------------------------------------
        HashSet<Character> alfabeto = new HashSet<>();

        String entradaAlfabeto = entrada.nextLine();

        for (int i = 0; i < entradaAlfabeto.length(); i++) {

            // Se o caractere for um espaco em branco, ele nao e adicionado no conjunto do
            // alfabeto
            if (!Character.isWhitespace(entradaAlfabeto.charAt(i))) {
                alfabeto.add(entradaAlfabeto.charAt(i));
            }
        }

        // System.out.println("Alfabeto: " + alfabeto);

        // ENTRADA DAS TRANSICOES DE ESTADO ----------------------------------------------
        ArrayList<String[]> transicoes = new ArrayList<>();

        String entradaTransicao = entrada.nextLine();

        String[] transicoesSeparadas = entradaTransicao.split("\\s+");

        for (String transicao : transicoesSeparadas) {
            // Dividindo a string "q0,2,q3", por exemplo, em "q0", "2" e "q3"
            String[] partes = transicao.split(",");
            transicoes.add(new String[] { partes[0], partes[1], partes[2] });
        }

        /*
         * System.out.print("Transicoes: ");
         * for (String[] transicao : transicoes) {
         * System.out.println(Arrays.toString(transicao) + " ");
         * }
         */

        // ENTRADA DO ESTADO INICIAL ----------------------------------------------------
        String estadoInicial = entrada.nextLine();

        // System.out.println("Estado inicial: " + estadoInicial);

        // ENTRADA DOS ESTADOS DE ACEITACAO ---------------------------------------------
        ArrayList<String[]> estadosDeAceitacao = new ArrayList<>();

        String entradaEstadosAceitacao = entrada.nextLine();

        // Dividindo a entrada em estados individuais
        String[] estadosAceitacaoArray = entradaEstadosAceitacao.split(" ");

        // Convertendo o array de strings em uma lista de arrays de strings
        for (String estado : estadosAceitacaoArray) {
            estadosDeAceitacao.add(new String[] { estado });
        }

        // System.out.print("Estados de aceitacao: ");
        // for (String[] estado : estadosDeAceitacao) {
        // System.out.println(Arrays.toString(estado));
        // }

        // ENTRADA DA PALAVRA QUE SERA ACEITA OU REJEITADA PELO AFD ---------------------
        String palavra = entrada.nextLine();

        // System.out.println(palavra);
        
        // Fechando a entrada do usuario
        entrada.close();

        // Inicializando o automato
        AFD maquina = new AFD(estados, alfabeto, transicoes, estadoInicial, estadosDeAceitacao);

        // Imprime na tela "aceita" ou "rejeita" 
        System.out.println(maquina.executar(palavra));
    }
}
