import java.util.Scanner;

public class ControleAcademico {

    private static String[] nomes;
    private static double[] notasAV1;
    private static double[] notasAV2;
    private static final int QTD = 100;
    private static int indiceAluno = 0;

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        nomes = new String[QTD];
        notasAV1 = new double[QTD];
        notasAV2 = new double[QTD];

        String opcao;

        do {
            System.out.println("Sistema de controle acadêmico");
            System.out.println("=============================");
            System.out.println();
            System.out.println("Selecione uma das opções a seguir:\n");
            System.out.println("[1] Registrar as notas de um novo aluno.");
            System.out.println("[2] Consultar boletim de um aluno.");
            System.out.println("[3] Consultar notas da turma.");
            System.out.println("[4] Sair");

            System.out.println("Informe a opção desejada");
            opcao = in.next();

            switch (opcao) {
                case "1":
                    registrarNotas();
                    break;
                case "2":
                    consultarNotasAluno();
                    break;
                case "3":
                    consultarNotasTurma();
                    break;
                case "4":
                    System.out.println("Finalizar");
                    break;
                default:
                    System.out.println("Opção inválica");
                    break;
            }
        } while (!opcao.equals("4"));

        in.close();
    }

    private static void registrarNotas() {
        System.out.println("\nRegistrar notas do aluno");
        System.out.println("------------------------\n");
        if (indiceAluno < QTD) {
            System.out.println("Informe o nome do aluno:");
            nomes[indiceAluno] = in.next();

            notasAV1[indiceAluno] = solicitaNota("1");
            notasAV2[indiceAluno] = solicitaNota("2");
            indiceAluno += 1;
        } else {
            System.out.println("=======================================================");
            System.out.println("Quantidade limite para cadastramento de notas esgotado.");
            System.out.println("=======================================================\n\n");
            System.out.println("Pressione uma tecla para continuar");
            in.next();
        }
    }

    private static double solicitaNota(String avaliacao){
        String nota;
        double retornoNota;
        while (true) {
            System.out.println("Informe a nota obtida na Avaliação " + avaliacao);
            nota = in.next();
            try {
                retornoNota = Double.parseDouble(nota);
                break;
            } catch (NumberFormatException e) {
                System.out.println("A nota tem que ser um número");
            }
        }
        return retornoNota;
    }

    private static void consultarNotasTurma() {
        if (indiceAluno != 0) {

            System.out.println("\nConsultar notas da turma");
            System.out.println("------------------------\n");
            System.out.println("Pos\tNome \t\tAV1 \tAV2 \tMédia \tResultado");
            for (int i = 0; i < indiceAluno; i++) {

                imprimir(i);
            }
            System.out.println("--------------------");
        } else {
            System.out.println("Não há nennhum registro de aluno!");
        }
        System.out.println("Pressione uma tecla para continuar");
        in.next();
    }

    private static void consultarNotasAluno() {
        String posStr;
        int posInt;
        if (indiceAluno != 0) {
            while (true) {
                System.out.println("Informe a posição do aluno:");
                posStr = in.next();
                try {
                    posInt = Integer.parseInt(posStr);
                    if (posInt >= indiceAluno) {
                        System.out.println("A posição do aluno deve ser um número e entre 0 e " + (indiceAluno - 1));
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("A posição do aluno deve ser um número e entre 0 e " + (indiceAluno - 1));
                }
            }
            System.out.println("\nConsultar notas do Aluno");
            System.out.println("------------------------\n");
            System.out.println("Pos\tNome \t\tAV1 \tAV2 \tMédia \tResultado");

            imprimir(posInt);

            System.out.println("--------------------");
        } else {
            System.out.println("Não há nennhum registro de aluno!");
        }

        System.out.println("Pressione uma tecla para continuar");
        in.next();
    }
    private static double calculaMediaNotas(int posicaoInt){
        return (notasAV1[posicaoInt] + notasAV2[posicaoInt]) / 2;
    }
    private static String retornaResultado(double media){
        if (media < 4) {
            return "Reprovado";
        } else if (media >= 4 && media < 7) {
            return "Prova final";
        } else {
            return "Aprovado";
        }

    }
    private static void imprimir(int posInt){
        double media = calculaMediaNotas(posInt);
        String resultado = retornaResultado(media);
        System.out.printf("%d\t%s \t\t%.1f \t%.1f \t%.1f \t%s \n", posInt, nomes[posInt], notasAV1[posInt], notasAV2[posInt], media, resultado);
    }
}
