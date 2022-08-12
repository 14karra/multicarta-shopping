export function extractJsonFromXml(response: Response): Promise<{}> {
    return response.text()
        .then(xmlString => {
                let parseString = require('xml2js').parseString;
                let jsonData = {};
                parseString(xmlString, function (err: any, data: any) {
                    console.log("Data received:\n" + JSON.stringify(data));
                    jsonData = data;
                });
                console.log("Returning data received:\n" + JSON.stringify(jsonData));
                return jsonData;
            }
        )
}

export function generateUserRequestXml(userRequest: {
    username: string, password: string, name: string, lastName: string, birthday: Date
}): string {
    let obj = {
        UserRegistrationRequest: {
            username: userRequest.username,
            password: userRequest.password,
            name: userRequest.name,
            lastName: userRequest.lastName,
            birthday: userRequest.birthday.toISOString().substring(0, 10),
            $: {
                "xmlns:xsi": "http://www.w3.org/2001/XMLSchema-instance",
                "xmlns": "urn:iso:std:ru:multicarta:tech:xsd:UserRegistrationRequest",
                "xsi:schemaLocation": "urn:iso:std:ru:multicarta:tech:xsd:UserRegistrationRequest schema.xsd"
            }
        }
    };

    let xml2js = require('xml2js');
    let builder = new xml2js.Builder();
    let xmlString = builder.buildObject(obj);
    console.log("User request string: \n" + xmlString);
    return xmlString;
}

export function generatePurchaseRequestXml(itemId: number, quantity: number): string {
    let obj = {
        PurchaseRequest: {
            itemId: itemId,
            quantity: quantity,
            $: {
                "xmlns:xsi": "http://www.w3.org/2001/XMLSchema-instance",
                "xmlns": "urn:iso:std:ru:multicarta:tech:xsd:PurchaseRequest",
                "xsi:schemaLocation": "urn:iso:std:ru:multicarta:tech:xsd:PurchaseRequest schema.xsd"
            }
        }
    };

    let xml2js = require('xml2js');
    let builder = new xml2js.Builder();
    let xmlString = builder.buildObject(obj);
    console.log("Purchase request string: \n" + xmlString);
    return xmlString;
}