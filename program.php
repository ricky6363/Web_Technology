<!DOCTYPE html>
<html>
<head>
    <title>Sports Stars</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid black;
            background-color:#39e75f;
        }

        th, td {
            padding: 8px;
            text-align: left;
            font-weight: bold;
            font-size:20px
        }

        th {
            background-color: #00DDFF;
        }

        ul {
            list-style-type: square;
        }
    </style>
</head>
<body >
    <h1>Sports Stars</h1>
    <table>
        <tr>
            <th>Name</th>
            <th>Sport</th>
            <th>Team</th>
            <th>Country</th>
            <th>Achievements</th>
        </tr>
        <?php
            // Load the XML file
            $xml = simplexml_load_file('sample.xml');

            // Check if the XML file was successfully loaded
            if ($xml) {
                // Loop through each athlete and display their information
                foreach ($xml->athlete as $athlete) {
                    echo "<tr>";
                    echo "<td>" . $athlete->name . "</td>";
                    echo "<td>" . $athlete->sport . "</td>";
                    echo "<td>" . $athlete->team . "</td>";
                    echo "<td>" . $athlete->country . "</td>";
                    
                    echo "<td>";
                    echo "<ul>";
                    foreach ($athlete->achievements->achievement as $achievement) {
                        echo "<li>" . $achievement . "</li>";
                    }
                    echo "</ul>";
                    echo "</td>";
                    echo "</tr>";
                }
            } else {
                echo "<tr><td colspan='5'>Failed to load the XML file.</td></tr>";
            }
        ?>
    </table>
</body>
</html>
