export async function postData(url = '', data = {}) {
    const response = await fetch(url, {
      method: 'POST',
      cache: 'no-cache',
      headers: {
        'Content-Type': 'application/json',
        'Accepts': 'application/json',
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Headers': 'Content-Type, Access-Control-Allow-Headers, Access-Control-Allow-Origin, Accept',
      },
      body: JSON.stringify(data)
    });
    console.log(JSON.stringify(data));
    return response;
}