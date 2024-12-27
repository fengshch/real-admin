import logoDark from "./logo-dark.svg";
import logoLight from "./logo-light.svg";
import { useEffect, useState } from "react";

function getCookie(name: string): string | null {
  const value = `; ${document.cookie}`;
  const parts = value.split(`; ${name}=`);
  if (parts.length === 2) return parts.pop()?.split(';').shift() ?? null;
  return null;
}

export function Welcome() {
  const [userInfo, setUserInfo] = useState<any>(null);
  const [error, setError] = useState<string | null>(null);
  const [messages, setMessages] = useState<string[]>([]);

  useEffect(() => {
    const fetchUserInfo = async () => {
      try {
        const response = await fetch(`http://localhost:8090/auth/userinfo`, {
        // const response = await fetch(`/auth/userinfo`, {
          credentials: 'include',
          headers: {
            'Accept': 'application/json'
          },
        });
        if (!response.ok) {
          console.log("response: ", response);
          throw new Error('Failed to fetch user info');
        }
        const data = await response.json();
        console.log("data: ", data);
        setUserInfo(data);
      } catch (err) {
        setError(err instanceof Error ? err.message : 'An error occurred');
        console.log("error: ", err);
        
        window.location.href = `http://localhost:8090`;
      }
    };

    fetchUserInfo();
  }, []);

  return (
    <div className="grid grid-rows-[20px_1fr_20px] items-center justify-items-center min-h-screen p-8 pb-20 gap-16 sm:p-20 font-[family-name:var(--font-geist-sans)]">
      <div className="flex gap-4">
        <button 
          className="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 transition-colors"
          onClick={() => window.location.href = `http://localhost:8090/oauth2/authorization/messaging-client-authorization-code`}
        >
          Click me
        </button>
        <button 
          className="px-4 py-2 bg-green-500 text-white rounded-md hover:bg-green-600 transition-colors"
          onClick={() => {
            fetch(`http://localhost:8090/messages01/hello`, {
            // fetch(`/messages01/hello`, {
              credentials: 'include',
              headers: {
                'X-XSRF-TOKEN': getCookie('XSRF-TOKEN') || '',
              },
            }).then(response =>{
              console.log(response);
              return response.json();
            }).then(data => {
              console.log(data);
              setMessages(data.data);
            })
            .catch(err =>{
              console.log("error: ", err);
              // setError(err instanceof Error ? err.message : 'An error occurred');
              // clearAllCookies();
              window.location.href = `http://localhost:8090`;
            });
          }}
        >
          Authorize Messages
        </button>
        <button 
          className="px-4 py-2 bg-red-500 text-white rounded-md hover:bg-red-600 transition-colors"
          onClick={async () => {
            await fetch(`${process.env.NEXT_PUBLIC_bbfUrl}/logout`, {
              method: 'POST',
              credentials: 'include',
              headers: {
                'X-XSRF-TOKEN': getCookie('XSRF-TOKEN') || '',
              },
            }).then(() => {
              console.log('Logged out');
              // window.location.href = 'http://127.0.0.1:8080';
              window.location.reload();
            })
            .catch(err => console.log(err));
          }}
        >
          Logout
        </button>
      </div>

      {error && (
        <div className="text-red-500">
          {error}
        </div>
      )}

      {userInfo && (
        <div className="mt-4">
          <pre className="bg-gray-100 p-4 rounded-md text-gray-800">
            {JSON.stringify(userInfo, null, 2)}
          </pre>
        </div>
      )}

      {messages && messages.length > 0 && (
        <div className="w-full max-w-2xl mt-4">
          <h2 className="text-xl font-bold mb-4">Messages</h2>
          <div className="space-y-4">
            {messages.map((message, index) => (
              <div key={index} className="bg-white p-4 rounded-lg shadow">
                <p className="text-gray-800">{message}</p>
              </div>
            ))}
          </div>
        </div>
      )}
    </div>
  );
}

