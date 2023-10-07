package Vectors;

public abstract class Vectors {
    /**
     * The dot product for two vectors(arrays) v1 = [a1, a2, ..., an] and v2 = [b1, b2, ... , bn]
     * is defined as p = sum(ai * bi) for all i up to n. For example, if v1 = [a1, a2, a3] and
     * v2 = [b1, b2, b3], their dot product would be equal to p = a1*b1 + a2*b2 + a3*b3.
     * @param v1 an Integer array.
     * @param v2 an Integer array.
     * @return an integer, the dot product of v1 and v2.
     * @throws Exception, if v1 and v2 have different sizes.
     */
    public static int dotProduct(Integer[] v1, Integer[] v2) throws Exception {
        if(v1.length != v2.length) throw new Exception("Vector sizes do not match");
        int dotProduct = 0;
        for (int i = 0; i < v1.length; i++) {
            dotProduct += v1[i] * v2[i];
        }
        return dotProduct;
    }
}
