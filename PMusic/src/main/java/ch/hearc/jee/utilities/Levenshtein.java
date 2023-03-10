
package ch.hearc.jee.utilities;

public class Levenshtein
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/***
	 * @ref https://www.techiedelight.com/calculate-string-similarity-java/
	 */
	public static int getDistance(String strOne, String strTwo)
		{
		int lengthOne = strOne.length();
		int lengthTwo = strTwo.length();

		int[][] matrix = new int[lengthOne + 1][lengthTwo + 1];

		for(int k = 1; k <= lengthOne; k++)
			{
			matrix[k][0] = k;
			}

		for(int k = 1; k <= lengthTwo; k++)
			{
			matrix[0][k] = k;
			}

		int cost;

		for(int i = 1; i <= lengthOne; i++)
			{
			for(int j = 1; j <= lengthTwo; j++)
				{
				cost = strOne.charAt(i - 1) == strTwo.charAt(j - 1) ? 0 : 1;
				matrix[i][j] = Integer.min(Integer.min(matrix[i - 1][j] + 1, matrix[i][j - 1] + 1), matrix[i - 1][j - 1] + cost);
				}
			}

		return matrix[lengthOne][lengthTwo];
		}

	/***
	 * @ref https://www.techiedelight.com/calculate-string-similarity-java/
	 */
	public static double getRatio(String strOne, String strTwo)
		{
		if (strOne == null || strTwo == null)
			{ throw new IllegalArgumentException("Strings must not be null"); }

		double maxLength = Double.max(strOne.length(), strTwo.length());

		if (maxLength > 0)
			{ return (maxLength - getDistance(strOne, strTwo)) / maxLength; }

		return 1.0;
		}
	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	}
