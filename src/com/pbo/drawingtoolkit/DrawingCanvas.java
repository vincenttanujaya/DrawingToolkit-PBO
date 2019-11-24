package com.pbo.drawingtoolkit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DrawingCanvas extends JPanel
{
    private LinkedList<DrawingObject> myShapes;
    private int currentShapeType;
    private DrawingObject currentShapeObject;
    private Color currentShapeColor;
    
    JLabel statusLabel;
    
    public DrawingCanvas(JLabel statusLabel){
        myShapes = new LinkedList<DrawingObject>();
        currentShapeType=0;
        currentShapeObject=null;
        currentShapeColor=Color.BLACK;
        
        this.statusLabel = statusLabel;
        setLayout(null);
        setBackground( Color.WHITE );
        add( statusLabel, BorderLayout.SOUTH );
        
        MouseHandler handler = new MouseHandler();                                    
        addMouseListener( handler );
        addMouseMotionListener( handler ); 
    }
    
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        
        ArrayList<DrawingObject> shapeArray=myShapes.getArray();
        for ( int counter=shapeArray.size()-1; counter>=0; counter-- )
           shapeArray.get(counter).draw(g);
        
        if (currentShapeObject!=null)
            currentShapeObject.draw(g);
    } 
    
    public void setCurrentShapeType(int type)
    {
        currentShapeType=type;
    }
    
    public void clearDrawing()
    {
        myShapes.makeEmpty();
        repaint();
    }
    
    private class MouseHandler extends MouseAdapter 
    {
    	public void mousePressed( MouseEvent event )
        {
            switch (currentShapeType)
            {
                case 0:
                    currentShapeObject= new Line( event.getX(), event.getY(), 
                                                   event.getX(), event.getY(), currentShapeColor);
                    break;
                case 1:
                	currentShapeObject= new Circle( event.getX(), event.getY(), 
                            event.getX(), event.getY(), currentShapeColor, false);
                	break;
            }
        }
        
    	public void mouseReleased( MouseEvent event )
        {
            currentShapeObject.setX2(event.getX());
            currentShapeObject.setY2(event.getY());
            
            myShapes.addFront(currentShapeObject);
            System.out.println(myShapes.getArray());
                  
            currentShapeObject=null;
            repaint();
        }
    	
    	public void mouseMoved( MouseEvent event )
        {
            statusLabel.setText(String.format("Mouse Coordinates X: %d Y: %d",event.getX(),event.getY()));
        }
    	
    	public void mouseDragged( MouseEvent event )
        {
            currentShapeObject.setX2(event.getX());
            currentShapeObject.setY2(event.getY());
            statusLabel.setText(String.format("Mouse Coordinates X: %d Y: %d",event.getX(),event.getY()));
            repaint();
        }
    }
}
