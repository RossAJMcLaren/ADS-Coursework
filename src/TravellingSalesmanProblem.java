import java.awt.geom.Point2D;
import java.io.*;
import java.util.*;

public class TravellingSalesmanProblem {

	public static void main(String[] args)
	{
		ArrayList<Point2D> lolImGood = TSPLibLoader.loadTSPLib("rl5934.tsp");
		double route = routeLength(lolImGood);
		System.out.println(route);

		lolImGood = nearestNeighbour(lolImGood);
		route = routeLength(lolImGood);
		System.out.println(route);
	}
	
	public static double routeLength(ArrayList<Point2D> cities)
	{
		//Calculate the length of a TSP route held in an ArrayList as a set of Points
		double result=0;//Holds the route length
		Point2D prev = cities.get(cities.size()-1);
		//Set the previous city to the last city in the ArrayList as we need to measure the length of the entire loop
		for(Point2D city : cities)
		{
			//Go through each city in turn
			result += city.distance(prev);
			//get distance from the previous city
			prev = city;
			//current city will be the previous city next time
		}
		return result;
	}
	
	public static ArrayList<Point2D> nearestNeighbour(ArrayList<Point2D> cities)
	{
		ArrayList<Point2D> result = new ArrayList<Point2D>();
		Random ran = new Random();
		Point2D currentCity = cities.remove(ran.nextInt(cities.size()));
		double distance;
		double bound = 6500;

		while(cities.size() > 0)
		{
			distance = Double.POSITIVE_INFINITY;
			ArrayList<Point2D> cluster = new ArrayList<Point2D>();
			for (Point2D possible : cities)
			{
				if(getDistance(currentCity, possible) < distance && getDistance(currentCity, possible) < bound)
				{
					distance = getDistance(currentCity, possible);
					cluster.add(possible);
				}
			}
			//System.out.println(cities.size());
			for(Point2D c : cluster)
			{
				cities.remove(cluster.get(cluster.indexOf(c)));
				result.add(cluster.get(cluster.indexOf(c)));
			}

			if(cities.size() != 0)
			{
			currentCity = farthestNeighbour(cities, cluster.get(ran.nextInt(cluster.size())));
			}
			System.out.println(cluster.size());
		}
		return result;
	}

	//Turns out that the farthest neighbour technique is a really bad solution to TSP.
	//It takes just as long as nearest neighbour to compute, but it gives back a really bad result.
	//Next I'm going to try and combine them, into 'Here, there, wherever you are Neighbour.'
	public static Point2D farthestNeighbour(ArrayList<Point2D> cities, Point2D city)
	{
		Point2D currentCity = city;
		double distance = 0;
		Point2D farthestCity = cities.get(0);

		for (Point2D possible : cities)
		{
			if (getDistance(currentCity, possible) > distance)
			{
				farthestCity = possible;
				distance = getDistance(currentCity, possible);
			}
		}
			currentCity = farthestCity;
		return farthestCity;
	}



	public static double getDistance(Point2D currentCity, Point2D possibleNextCity)
	{
		return currentCity.distance(possibleNextCity);
	}

}
