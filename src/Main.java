import java.util.Arrays;

public class Main {

    // Test amacıyla main fonksiyonu
    public static void main(String[] args) {
        // Solution sınıfının bir örneğini oluşturuyoruz
        Solution solution = new Solution();

        // Test verileri
        int[][] edges1 = { {1, 2}, {1, 3}, {2, 3} };
        // Fazladan kenarı bulup yazdırıyoruz
        System.out.println(Arrays.toString(solution.findRedundantConnection(edges1))); // Çıktı: [2, 3]

        int[][] edges2 = { {1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5} };
        // Fazladan kenarı bulup yazdırıyoruz
        System.out.println(Arrays.toString(solution.findRedundantConnection(edges2))); // Çıktı: [1, 4]
    }
}

class Solution {

    // Union-Find veri yapısını tanımlıyoruz
    class UnionFind {
        private int[] parent; // Her düğümün kökünü saklayan dizi
        private int[] rank;   // Her kökün derecesini saklayan dizi

        // Union-Find yapısının başlatılması
        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i; // Başlangıçta her düğüm kendi ebeveyni
                rank[i] = 1; // Başlangıçta rank 1
            }
        }

        // Bir düğümün kökünü bulma (path compression ile)
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }

        // İki kümenin birleştirilmesi (union by rank ile)
        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {
                return false; // Aynı kökte ise döngü oluşur
            }

            // Union by rank: Daha küçük rankli kökü büyük rankli kökün altına koy
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }

            return true;
        }
    }

    // Fazladan kenarı bulan ana fonksiyon
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length; // Kenar sayısı
        UnionFind uf = new UnionFind(n + 1); // Union-Find yapısını başlat (1-indexed)

        // Her kenarı işleyerek döngü olup olmadığını kontrol ediyoruz
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            // Eğer union işlemi başarısızsa, bu kenar döngü oluşturuyor
            if (!uf.union(u, v)) {
                return new int[] { u, v }; // Fazladan kenarı döndür
            }
        }

        return new int[0]; // Bu noktaya gelinmemeli (falanlık durum)
    }
}
