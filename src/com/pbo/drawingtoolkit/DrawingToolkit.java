package com.pbo.drawingtoolkit;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.*;

public class DrawingToolkit
{
    public static void main( String args[] )
    {
        DrawingFrame canvasGUI = new DrawingFrame();
        canvasGUI.setSize(new Dimension(600, 600));
    }   
}