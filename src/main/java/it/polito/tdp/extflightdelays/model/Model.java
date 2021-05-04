package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	Graph<Airport,DefaultWeightedEdge> grafo;
	ExtFlightDelaysDAO flightDAO;
	Map<Integer,Airport> mappa;
	Integer contoV;
	Integer contoA;
	
	
	public Model()
	{
		flightDAO=new ExtFlightDelaysDAO();
		mappa=flightDAO.loadAllAirports();
	}
	public void creaGrafo(Integer distanzaM)
	{
		this.grafo=new SimpleWeightedGraph<Airport,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, mappa.values());
		for(Flight f:flightDAO.loadAllFlights(distanzaM))
		{
			Graphs.addEdge(this.grafo, mappa.get(f.getOriginAirportId()),mappa.get(f.getDestinationAirportId()),f.getDistance());
		}
		contoV=this.mappa.size();
		contoA=this.grafo.edgeSet().size();
		
	}
	public Graph<Airport, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}
	public void setGrafo(Graph<Airport, DefaultWeightedEdge> grafo) {
		this.grafo = grafo;
	}
	public ExtFlightDelaysDAO getFlightDAO() {
		return flightDAO;
	}
	public void setFlightDAO(ExtFlightDelaysDAO flightDAO) {
		this.flightDAO = flightDAO;
	}
	public Map<Integer, Airport> getMappa() {
		return mappa;
	}
	public void setMappa(Map<Integer, Airport> mappa) {
		this.mappa = mappa;
	}
	public Integer getContoV() {
		return contoV;
	}
	public void setContoV(Integer contoV) {
		this.contoV = contoV;
	}
	public Integer getContoA() {
		return contoA;
	}
	public void setContoA(Integer contoA) {
		this.contoA = contoA;
	}
	
	
	
	

}
