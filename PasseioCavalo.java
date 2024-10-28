public class PasseioCavalo {

    int n; //dimensão do tabuleiro
    int[][] tabuleiro; //matriz n*n que representa o tabuleiro
    int[] delta_x = { 2, 1, -1, -2, -2, -1, 1, 2 }; //deslocamentos possíveis na horizontal
    int[] delta_y = { 1, 2, 2, 1, -1, -2, -2, -1 }; //deslocamentos possíveis na vertical

    boolean posicaoValida(int x, int y) { //verifica se o movimento é válido
        return ((x >= 0) && (x < n) && (y >= 0) && (y < n) && (tabuleiro[x][y] == 0));
    }

    void imprimeTabuleiro() { //imprime o tabuleiro
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                System.out.print(this.tabuleiro[i][j] + "\t");
            }
            System.out.println("\n");
        }
    }

    void movePeca(int x, int y, int id) { //movimenta o cavalo para a posição indicada
        this.tabuleiro[x][y] = id;
    };

    boolean resolveRecursivo(int x, int y, int id) { //método recursivo para resolução do problema

        if (id > this.n * this.n) { //verifica se todas as casas foram preenchidas
            return true;
        }

        for (int i = 0; i < this.delta_x.length; i++) { //testa os movimentos possíveis

            int novo_x = x + this.delta_x[i];
            int novo_y = y + this.delta_y[i];

            if (posicaoValida(novo_x, novo_y)) { //se posição é válida, o cavalo se move
                movePeca(novo_x, novo_y, id);
                if (resolveRecursivo(novo_x, novo_y, id + 1)) { //verifica próximo movimento
                    return true;
                } else {
                    movePeca(novo_x, novo_y, 0); //backtrack
                }
            }

        }

        return false;

    }

    void resolve(int x, int y) { //encapsula método recursivo

        int jogadas = 1;

        movePeca(x, y, jogadas);

        resolveRecursivo(x, y, jogadas+1);

        imprimeTabuleiro();

    }

    public PasseioCavalo(int n) { //construtor

        this.tabuleiro = new int[n][n];
        this.n = n;

    }

    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]); //recebe a dimensão do tabuleiro como primeiro parâmetro

        int x = 0;

        int y = 0;

        PasseioCavalo passeio = new PasseioCavalo(n); //instancia a classe

        passeio.resolve(x, y); //invoca o método

    }

}