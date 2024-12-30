import { useEffect, useState } from 'react';
import './App.css';

// axios 라이브러리 사용
// 네트워크 호출에 많이 이용되는 다른 라이브러리로 axios가 있다.
// axios라이브러리에는 JSON데이터의 자동 변환과 같은 몇가지 이점이 있다.
function App() {
  const [temp, setTemp] = useState("")
  const [desc, setDesc] = useState("")
  const [icon, setIcon] = useState("")
  const [isReady, setIsReady] = useState(false)

  useEffect(()=>{
    fetch("https://api.openweathermap.org/data/2.5/weather?q=Busan&appid={5cc9fa363b73bae7ed9afc983d4dee49}")
    .then(result=>result.json())
    .then(jsonResult=>{
    console.log(jsonResult)
    setTemp(jsonResult.main.temp)
    setDesc(jsonResult.weather[0].main)
    setIcon(jsonResult.weather[0].icon)
    setIsReady(true)
  })
  .catch(err=>console.log(err))
  }, [])

  if(isReady) {
    return (
      <div className="App">
        <p>Temperature: {temp}</p>
        <p>Description: {desc}</p>
        <img src="https://openweathermap.org/img/wn/${icon}@2x.png"></img>
      </div>
    );
  }else {
    return <div>Loading...</div>
  }
}

export default App;
