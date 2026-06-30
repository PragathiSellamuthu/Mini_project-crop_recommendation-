# AI Based Crop Recommendation System

A simple Java-based web application mini-project that predicts the best crop based on soil and weather conditions using K-Nearest Neighbors (KNN with K=1) using Euclidean Distance.

## Project Structure
This structure follows Eclipse's Dynamic Web Project format:
- `src/com/crop/CropServlet.java`: Java Servlet backend for running with a Tomcat Server.
- `src/com/crop/SimpleHttpServer.java`: Standalone Java HTTP Server (pure Java Sockets, does not require Tomcat).
- `WebContent/index.html`: The input form.
- `WebContent/style.css`: UI styling.
- `WebContent/result.jsp`: JSP result page (used by Tomcat/Servlet setup).
- `WebContent/Crop_recommendation.csv`: Small subset dataset for prediction distances.
- `WebContent/WEB-INF/web.xml`: Configuration for servlet routing (used by Tomcat/Servlet setup).

---

## How to Run

There are two ways to run this project:

### Option A: Standalone Java Server (Easiest - No Tomcat needed!)
This runs a lightweight, built-in Java server that doesn't require any complex Eclipse server configurations.

1. Open Eclipse and import the project files.
2. Locate `src/com/crop/SimpleHttpServer.java`.
3. **Right-click** on `SimpleHttpServer.java` -> **Run As** -> **Java Application**.
4. Once the console says `"AI Crop Recommendation Server started on port 8080"`, open your web browser and go to:
   👉 **[http://localhost:8080/index.html](http://localhost:8080/index.html)**

---

### Option B: Dynamic Web Project (Tomcat Server)
If you want to run this as a standard Java EE Web Servlet under Apache Tomcat:

1. **Create a Dynamic Web Project** in Eclipse named `CropRecommendationSystem`.
2. **Copy the Files**:
   - Copy `src/com/` and its Java files into your project's Java source directory (e.g., `src/main/java/` or `src/`).
   - Copy all contents from the `WebContent/` folder into your project's web content folder (e.g., `src/main/webapp/` or `WebContent/`).
3. **Target Runtime**: Ensure your project is configured with Apache Tomcat v9.0 (via project Properties -> Targeted Runtimes).
4. **Run**: Right-click the project folder -> **Run As** -> **Run on Server**.
5. The application will be live at:
   👉 **[http://localhost:8080/CropRecommendationSystem/index.html](http://localhost:8080/CropRecommendationSystem/index.html)**

---

## Test Data Examples (derived from dataset)
You can enter these values in the form to test the recommendation engine:

### Test Case 1: Rice
- **Nitrogen (N)**: 90
- **Phosphorus (P)**: 42
- **Potassium (K)**: 43
- **Temperature**: 20.8
- **Humidity**: 82.0
- **pH**: 6.5
- **Rainfall**: 202.9

### Test Case 2: Maize
- **Nitrogen (N)**: 104
- **Phosphorus (P)**: 18
- **Potassium (K)**: 30
- **Temperature**: 23.6
- **Humidity**: 60.3
- **pH**: 6.7
- **Rainfall**: 140.9
