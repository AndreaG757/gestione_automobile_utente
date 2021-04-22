<!doctype html>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Modifica Elemento</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Modifica elemento</h5> 
		    </div>
		    <div class='card-body'>

					<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>

					<form method="post" action="ExecuteUpdateAutomobileServlet" novalidate="novalidate">
					
						<div class="form-row">
							<div class="form-group col-md-6">
							
								<label>Marca <span class="text-danger">*</span></label>
								<input type="text" name="marca" id="marca" value="${automobile_da_modificare.marca}" class="form-control" placeholder="Inserire la marca" required>
								
							</div>
							
							<div class="form-group col-md-6">
							
								<label>Modello <span class="text-danger">*</span></label>
								<input type="text" name="modello" id="modello" value="${automobile_da_modificare.modello}"  class="form-control"  placeholder="Inserire il modello" required>
								
							</div>
						</div>
						
						<div class="form-row">	
							<div class="form-group col-md-6">
							
								<label>Cilindrata <span class="text-danger">*</span></label>
								<input type="number" class="form-control" name="cilindrata" id="cilindrata"  value="${automobile_da_modificare.cilindrata}" placeholder="Inserire la cilindrata" required>
								
							</div>
							<div class="form-group col-md-3">
							
								<label>Data di Immatricolazione<span class="text-danger">*</span></label>
								<fmt:formatDate var="dataImmatricolazioneParsed" value="${automobile_da_modificare.dataImmatricolazione}" type="date" pattern="yyyy-MM-dd"/>
                        		<input class="form-control" id="dataImmatricolazione" value="${dataImmatricolazioneParsed}" type="date" placeholder="dd/MM/yyyy"
                            		   title="formato : gg/mm/aaaa"  name="dataImmatricolazione" required>
                            		
							</div>
							<input type="hidden" name="idAutomobile" value="${automobile_da_modificare.id}">
						</div>
							
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
						<a class="btn btn-outline-secondary" href="ListAutomobiliServlet">Annulla</a>

					</form>

		    
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>