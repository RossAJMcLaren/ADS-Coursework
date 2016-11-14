import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TSPLibLoader {
	
	public static ArrayList<City> loadTSPLib(String fName)
	{
		//Load in a TSPLib instance. This example assumes that the Edge weight type
		//is EUC_2D.
		//It will work for examples such as rl5915.tsp. Other files such as
		//fri26.tsp .To use a different format, you will have to
		//modify the this code
		
		
		ArrayList<City> result = new ArrayList<City>();
		BufferedReader br = null;
		try {
		String currentLine;
		int dimension =0;//Hold the dimension of the problem
		boolean readingNodes = false;
		br = new BufferedReader(new FileReader(fName));
		while ((currentLine = br.readLine()) != null) 
		{
			//Read the file until the end;
			if (currentLine.contains("EOF"))
			{
				//EOF should be the last line
				readingNodes = false;
				//Finished reading nodes
				if (result.size() != dimension)
				{
					//Check to see if the expected number of cities
					//have been loaded
					System.out.println("Error loadingcities");
					System.exit(-1);
				}
			}
			if (readingNodes)
			{
				//If reading in the node data
				String[] tokens = currentLine.split(" ");
				//Split the line by spaces.
				//tokens[0] is the city id and not needed in thisexample
				float x = Float.parseFloat(tokens[1].trim());
				float y = Float.parseFloat(tokens[2].trim());
				//Use Java's built in Point2D type to hold a city
				City city = new City();
				city.cityCoOrdinates = new Point2D.Float(x,y);
				//Add this city into the arraylist
				result.add(city);
			}
			if (currentLine.contains("DIMENSION"))
			{
				//Note the expected problem dimension (number ofcities)
				String[] tokens = currentLine.split(":");
				dimension = Integer.parseInt(tokens[1].trim());
			}
			if (currentLine.contains("NODE_COORD_SECTION"))
			{
				//Node data follows this line
				readingNodes = true;
			}
		}
		} catch (IOException e) 
		{
			e.printStackTrace();
		} finally 
		{
		try 
		{
			if (br != null)br.close();
		} catch (IOException ex) 
		{
			ex.printStackTrace();
		}
		}
		return result;
	}
}
