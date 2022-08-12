export default async function fetchXml(path: string): Promise<Response> {
    return fetch(path, {
        method: 'GET',
        headers: {
            'Accept': 'application/xml',
        },
    });
}

export async function fetchPlainText(path: string): Promise<Response> {
    return fetch(path, {
        method: 'GET',
        headers: {
            'Accept': 'text/plain'
        },
    });
}

export async function postRequest(path: string, xmlBody: string): Promise<Response> {
    return fetch(path, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/xml',
            'Accept': 'application/xml'
        },
        body: xmlBody
    });
}