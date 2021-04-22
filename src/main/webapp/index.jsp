<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it">
  <head>
    
    <jsp:include page="./header.jsp" />
    
    <!-- Custom styles for this template -->
    <link href="./assets/css/global.css" rel="stylesheet">
    <style type="text/css">
    	body {
		  padding-top: 3.5rem;
		}	
    </style>
    
    <title>Gestione delle Automobili</title>
  </head>
  <body>
  
	<jsp:include page="./navbar.jsp"></jsp:include>
  
  
	<main role="main" class="container">
	
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Ricerca elemento</h5> 
		    </div>
		    <div class='card-body'>

				<form method="post" action="ExecuteFindAutomobileServlet" novalidate="novalidate">
				
					<div class="form-row">
						<div class="form-group col-md-6">
						
							<label>Marca</label>
							<input type="text" name="marca" id="marca" class="form-control" value="${auto_find.marca}"  placeholder="Inserire la marca" required>
							
						</div>
						
						<div class="form-group col-md-6">
						
							<label>Modello</label>
							<input type="text" name="modello" id="modello" value="${auto_find.modello}" class="form-control" placeholder="Inserire il modello" required>
							
						</div>
					</div>
					
					<div class="form-row">	
						<div class="form-group col-md-6">
						
							<label>Cilindrata</label>
							<input type="number" class="form-control" name="cilindrata" id="cilindrata"  value="${auto_find.cilindrata}" placeholder="Inserire la Cilindrata" required>
							
						</div>
						<div class="form-group col-md-3">
						
							<label>Data di Immatricolazione</label>
							<fmt:formatDate var="dataImmatricolazioneParsed" value="${auto_find.dataImmatricolazione}" type="date" pattern="yyyy-MM-dd"/>
                       		<input class="form-control" value="${dataImmatricolazioneParsed}" id="dataImmatricolazione" type="date" placeholder="gg/mm/aaaa"
                           		   title="formato : gg/mm/aaaa"  name="dataImmatricolazione" required>
                           		   
						</div>
						
					</div>
						
					<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
					<a class="btn btn-primary " href="PrepareInsertAutomobileServlet">Aggiungi Nuovo</a>

				</form>
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	
	<jsp:include page="./footer.jsp" />
  </body>
</html>
