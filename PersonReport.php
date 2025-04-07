<!--Szymon Stepniak
	C00300283
	Lab 5 Task 1	-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Person Report</title>
    <?php
	//include "menu.php";
    include 'db.inc.php'; 
    date_default_timezone_set('UTC');
    ?>
</head>
<body>
    <form action="PersonReport.php" method="post" name="reportForm">
        <input type="hidden" name="choice">
    </form>
    <h1>Person Report</h1>
    <h3>(click a button to see the Person Report in the desired order)</h3>
    <input type='button' id="dateButton" value='date of birth' onclick='dateOrder()' title="Click here to see the persons in reverse date of birth order">
    <input type='button' id="nameButton" value='Surname Order' onclick='surnameOrder()' title="Click here to see the persons in alphabetical order of surname">
    <br><br>
    <script>
        function dateOrder(){
            document.reportForm.choice.value = "DOB";
            document.reportForm.submit();
        }
        function surnameOrder(){
            document.reportForm.choice.value = "Surname";
            document.reportForm.submit();
        }
    </script>
    <?php
    $choice = "Surname";
    if(isset($_POST['choice'])){
        $choice = $_POST['choice'];
    } 
    if($choice == "DOB"){
    ?>
        <script>
            document.getElementById("dateButton").disabled = true;
            document.getElementById("nameButton").disabled = false;
        </script>
    <?php
        $sql = "Select * FROM Persons Where DeleteFlag = false ORDER BY DOB DESC";  // Added semicolon here
        produceReport($con, $sql);
    }
    else { // Default case for Surname
    ?>
        <script>
            document.getElementById("nameButton").disabled = true;
            document.getElementById("dateButton").disabled = false;
        </script>
    <?php
        $sql = "Select * FROM Persons where DeleteFlag = false ORDER BY LastName";  // Added semicolon here
        produceReport($con, $sql);
    }
    
    function produceReport($con, $sql){
        $result = mysqli_query($con, $sql);
        echo "<table border='1'>
                <tr><th>Surname</th><th>Firstname</th><th>Date of Birth</th></tr>";
        while($row = mysqli_fetch_array($result)){
            $date = date_create($row['DOB']);
            $FDate = date_format($date, "d/m/Y");
            echo "<tr>
                    <td>".$row['lastName']."</td>
                    <td>".$row['firstName']."</td>
                    <td>".$FDate."</td>
                  </tr>";
        }
        echo "</table>";
    }
    mysqli_close($con);
    ?>
</body>
</html>
