<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<html>
<head>
<title>Hibernate/JPA concurrency demo</title>
</head>
<body>

	<p>Order status: ${command.status}.</p>

	<form:form>
		<div>
			<form:label path="description">Order detail: </form:label>
			<form:textarea path="description" />
		</div>
		<div>
			<form:label path="status">Change status: </form:label>
			<form:select path="status">
				<form:option value="Approved" />
				<form:option value="Rejected" />
			</form:select>
		</div>
		<form:hidden path="id" />
		<form:hidden path="version" />
		<input type="submit" value="Submit" />
	</form:form>

</body>
</html>

