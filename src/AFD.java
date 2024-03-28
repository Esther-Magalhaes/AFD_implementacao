import java.util.*;

public class AFD {

    HashSet<String> estados;
    HashSet<Character> alfabeto;
    ArrayList<String[]> transicoes;
    String estadoInicial;
    ArrayList<String[]> estadosDeAceitacao;
    HashMap<String, String> mapa = new HashMap<>();

    AFD(HashSet<String> estados, HashSet<Character> alfabeto, ArrayList<String[]> transicoes, String estadoInicial,
            ArrayList<String[]> estadosDeAceitacao) {
        this.estados = estados;
        this.alfabeto = alfabeto;
        this.transicoes = transicoes;
        this.estadoInicial = estadoInicial;
        this.estadosDeAceitacao = estadosDeAceitacao;

        // Processando as transicoes e adicionando no mapa
        for (String[] transicao : transicoes) {
            // transicao[0] e a origem
            // transicao[1] e o simbolo do alfabeto
            // transicao[2] e o destino
            String chave = transicao[0] + "," + transicao[1];
            mapa.put(chave, transicao[2]); // Adicionando as transicoes (chave, valor), chave = origem + simbolo do
                                           // alfabeto, valor = destino
        }
    }

    // Executa o automato com a cadeia de palavra dada
    String executar(String palavra) {

        // Começa no estado inicial
        String estadoAtual = estadoInicial;

        // Verifica se a palavra esta vazia
        if (palavra.isEmpty()) {
            // Se a palavra for vazia e o estado inicial estiver entre os estados de
            // aceitacao, ela e aceita
            if (verificaEstadoDeAceitacao(estadoInicial)) {
                return "aceita";
            } else {
                return "rejeita";
            }
        }

        Boolean encontrouTransicao = false;

        // Para cada letra da palavra dada, verificamos se existe um estado de destino,
        // a partir do estado atual
        for (int i = 0; i < palavra.length(); i++) {

            for (String[] transicao : transicoes) {

                // Verifica se ha uma transicao do estado atual com o simbolo atual
                if (mapa.containsKey(estadoAtual + ',' + transicao[1]) && transicao[1].charAt(0) == palavra.charAt(i)
                        && encontrouTransicao == false) {

                    // Atualiza o estado atual
                    estadoAtual = mapa.get(estadoAtual + ',' + transicao[1]);

                    // Se achou a transicao, entao nao precisa procurar mais, pode passar para o
                    // proximo simbolo da palavra
                    encontrouTransicao = true;

                    // Verifica se chegamos em algum estado de aceitacao e se nao existe mais
                    // nenhum simbolo para ser lido da palavra de processamento
                    if (verificaEstadoDeAceitacao(estadoAtual) && i == palavra.length() - 1) {
                        return "aceita";
                    }
                }
            }
            encontrouTransicao = false;
        }
        return "rejeita";
    }

    // Verifica se o estado atual esta no estado de aceitacao
    boolean verificaEstadoDeAceitacao(String estadoAtual) {
        for (String[] estado : estadosDeAceitacao) {
            // Compara cada estado de aceitacao com o estado atual
            if (estado[0].equals(estadoAtual)) {
                return true;
            }
        }
        return false;
    }
}
