// Much of the code is similar to the first request here the
// difference is we are only allowing valid xml

//Using xml2js to parse the recieved payload
//What this is will do is parse only valid xml
//And also converts xml to javascript object



const http = require('http')
const parseString = require('xml2js').parseString;


const server = http.createServer((req, res) => {
    const recievedData = []
    if(req.url === '/xml'){

        req.on('data',(chunk) => { 
            recievedData.push(chunk)
        }).on('end',() => {
            parseString(recievedData, function (err, result) {
                //Result will be undefined if its not a valid xml
                // console.log(result)
                if(!result){ 
                    res.statusCode = 400
                    res.end("failed")
                }else{
                    res.statusCode = 200
                    res.end("success")
                }
            });
            //We can log what data was send from post request
            console.log(recievedData.toString())
        })
        
    }
})

//Server Running on PORT you can change according to your preference
server.listen(2000,() => console.log("Server Listening"))