package com.crop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CropServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Helper class to hold dataset rows
    class CropData {
        double n, p, k, temperature, humidity, ph, rainfall;
        String label;

        public CropData(double n, double p, double k, double temperature, double humidity, double ph, double rainfall, String label) {
            this.n = n;
            this.p = p;
            this.k = k;
            this.temperature = temperature;
            this.humidity = humidity;
            this.ph = ph;
            this.rainfall = rainfall;
            this.label = label;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get user inputs
            double n = Double.parseDouble(request.getParameter("n"));
            double p = Double.parseDouble(request.getParameter("p"));
            double k = Double.parseDouble(request.getParameter("k"));
            double temp = Double.parseDouble(request.getParameter("temperature"));
            double hum = Double.parseDouble(request.getParameter("humidity"));
            double ph = Double.parseDouble(request.getParameter("ph"));
            double rain = Double.parseDouble(request.getParameter("rainfall"));

            // Read the dataset
            List<CropData> dataset = new ArrayList<>();
            // Accessing the CSV placed in the WebContent folder
            InputStream is = getServletContext().getResourceAsStream("/Crop_recommendation.csv");
            
            if (is == null) {
                request.setAttribute("error", "Error: Dataset 'Crop_recommendation.csv' not found. Please place it in the WebContent folder.");
                request.getRequestDispatcher("result.jsp").forward(request, response);
                return;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            boolean isFirstLine = true;
            
            while ((line = br.readLine()) != null) {
                if (isFirstLine) { // Skip header row
                    isFirstLine = false;
                    continue;
                }
                
                String[] values = line.split(",");
                if (values.length >= 8) {
                    try {
                        CropData data = new CropData(
                            Double.parseDouble(values[0].trim()),
                            Double.parseDouble(values[1].trim()),
                            Double.parseDouble(values[2].trim()),
                            Double.parseDouble(values[3].trim()),
                            Double.parseDouble(values[4].trim()),
                            Double.parseDouble(values[5].trim()),
                            Double.parseDouble(values[6].trim()),
                            values[7].trim()
                        );
                        dataset.add(data);
                    } catch (NumberFormatException e) {
                        // Skip rows with invalid formatted numbers
                    }
                }
            }
            br.close();

            // Find the closest match using Euclidean Distance (K-Nearest Neighbor with K=1)
            String bestCrop = "Unknown";
            double minDistance = Double.MAX_VALUE;

            for (CropData data : dataset) {
                double distance = Math.pow(n - data.n, 2) +
                                  Math.pow(p - data.p, 2) +
                                  Math.pow(k - data.k, 2) +
                                  Math.pow(temp - data.temperature, 2) +
                                  Math.pow(hum - data.humidity, 2) +
                                  Math.pow(ph - data.ph, 2) +
                                  Math.pow(rain - data.rainfall, 2);
                                  
                if (distance < minDistance) {
                    minDistance = distance;
                    bestCrop = data.label;
                }
            }

            // Set result and forward to JSP
            request.setAttribute("recommendedCrop", bestCrop);
            request.getRequestDispatcher("result.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while processing the request. Please ensure all parameter inputs are valid numbers.");
            request.getRequestDispatcher("result.jsp").forward(request, response);
        }
    }
}
