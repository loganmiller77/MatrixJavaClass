public class Matrix {
    private double[][] matrix;

    public Matrix(double[][] input)
    {
        matrix=input;
    }

    public Matrix()
    {
        matrix = new double[][] {{0,0},{0,0}};
    }

    public void print()
    {
        for (int row=0;row<matrix.length;row++)
        {
            for (int col=0;col<matrix[0].length;col++)
            {
                System.out.print(matrix[row][col]+ " ");
            }
            System.out.println("");
        }
    }

    public Matrix ref()
    {
        double[][] newArray = new double[matrix.length][];
        for (int i=0;i<matrix.length;i++)
        {
            newArray[i] = matrix[i].clone();
        }
        Matrix newMatrix=new Matrix(newArray);
        for (int i = 0; i<newMatrix.matrix.length;i++)
        {
            if (newMatrix.matrix[i][i]==0)
            {
                int tempIndex = i;
                while (tempIndex<newMatrix.matrix.length-1)
                {
                    if(newMatrix.matrix[tempIndex+1][i]!=0)
                    {
                        for (int col = 0; col < newMatrix.matrix[0].length; col ++)
                        {
                            double tempVal = newMatrix.matrix[i][col];
                            newMatrix.matrix[i][col]=newMatrix.matrix[tempIndex+1][col];
                            newMatrix.matrix[tempIndex+1][col] = tempVal;
                        }
                        break;
                    }
                    tempIndex+=1;
                }
            }
            for (int row = i+1;row<newMatrix.matrix.length;row++)
            {
                double commonMultiplier;
                if (newMatrix.matrix[i][i]!=0)
                {
                    commonMultiplier = newMatrix.matrix[row][i]/newMatrix.matrix[i][i];
                }
                else
                    commonMultiplier = 0;
                for (int col = i; col<newMatrix.matrix[0].length;col++)
                {
                    newMatrix.matrix[row][col] = newMatrix.matrix[row][col]-commonMultiplier*newMatrix.matrix[i][col];

                }

            }
        }
        return newMatrix;
    }
    public Matrix rref()
    {
        Matrix newMatrix = this.ref();

        return newMatrix;
    }
    public double[] eigenvalues()
    {
        if (matrix.length != matrix[0].length)
        {
            double[] error = {-1};
            return error;
        }
        double[] eigenvalues = new double [matrix.length];
        Matrix newMatrix = this.ref();
        for (int i=0; i<matrix.length; i++)
        {
            eigenvalues[i] = newMatrix.matrix[i][i];
        }
        return eigenvalues;
    }

    public Matrix multiply(double scalar)
    {
        double[][] newArray = new double[matrix.length][];
        for (int i=0;i<matrix.length;i++)
        {
            newArray[i] = matrix[i].clone();
        }
        Matrix newMatrix=new Matrix(newArray);
        for (int row=0;row<matrix.length;row++)
        {
            for (int col=0;col<matrix[0].length;col++)
            {
                newMatrix.matrix[row][col]=scalar*matrix[row][col];
            }
        }
        return newMatrix;
    }

    public Matrix add(Matrix other)
    {
        double[][] newArray = new double[matrix.length][];
        for (int i=0;i<matrix.length;i++)
            {
                newArray[i] = matrix[i].clone();
            }
        Matrix newMatrix=new Matrix(newArray);
        for (int row=0;row<matrix.length;row++)
        {
            for (int col=0; col<matrix[0].length; col++)
            {
                newMatrix.matrix[row][col]=matrix[row][col]+other.matrix[row][col];
            }
        }
        return newMatrix;
    }
    public Matrix multiply(Matrix other)
    {
        /*if (matrix[0].length != other.matrix.length)
        {
            System.out.println("Invalid dimensions to multiply matrices.");
            int[][] errorArray = {{-1,-1} ,{-1,-1}};
            Matrix errorMatrix = new Matrix(errorArray);
            return errorMatrix;
        }*/
        double[][] newArray = new double[matrix.length][];
        for (int i=0;i<matrix.length;i++)
        {
            newArray[i] = matrix[i].clone();
        }
        Matrix newMatrix = new Matrix(newArray);
        Matrix transposedMatrix = other.transpose();
        for (int row = 0; row < matrix.length; row++)
        {
            for (int col = 0; col < matrix[0].length; col++)
            {
                newMatrix.matrix[row][col]=dotProduct(matrix[row],transposedMatrix.matrix[col]);
            }
        }
        return newMatrix;
    }

    public Matrix transpose()
    {
         double newArray[][]= new double[matrix[0].length][matrix.length];

         for (int row = 0; row < matrix.length; row++)
         {
             for (int col = 0; col < matrix[0].length; col++)
             {
                 newArray[col][row]=matrix[row][col];
             }
         }
         Matrix newMatrix = new Matrix(newArray);
         return newMatrix;
    }


    public double dotProduct(double[] array1, double[] array2)
    {
        if (array1.length != array2.length)
        {
            System.out.println("Invalid dimensions.");
            return -1;
        }
        else{
            double sum = 0;
            for (int i = 0; i < array1.length; i++)
            {
                sum += array1[i]*array2[i];
            }
            return sum;
        }


    }

    public double determinant()
    {
        if (matrix.length != matrix[0].length)
        {
            System.out.println("Could not computer determinant - matrix not square.");
            return -1;
        }
        return 0;

    }
    public static void main (String[] args)
    {
        double[][] matrix = new double[][] {{1,2},{2,1}};
       Matrix matrix1 = new Matrix(matrix);
       Matrix matrix2 = matrix1.ref();
       matrix2.print();
       double[] eigenvalues = matrix1.eigenvalues();
       for (int i=0;i<eigenvalues.length; i++)
       {
           System.out.println(eigenvalues[i]);
       }

    }
}
