function hideAll(){
document.querySelectorAll("section").forEach(sec=>sec.classList.remove("active"));
}

function login(){
if(loginUser.value && loginPass.value){
hideAll();
languageSection.classList.add("active");
}
else alert("Enter login details");
}

function goLoanForm(){
hideAll();
loanFormSection.classList.add("active");
}

let banks=[
{name:"SBI",home:8.5,car:9.1,personal:11.2,education:8.0},
{name:"HDFC",home:8.7,car:9.5,personal:12.0,education:9.0},
{name:"ICICI",home:8.9,car:9.3,personal:11.8,education:9.2},
{name:"Axis",home:9.0,car:9.7,personal:12.5,education:9.5},
{name:"PNB",home:8.4,car:9.2,personal:11.0,education:8.5}
];

function analyzeLoan(){

let type=loanType.value.toLowerCase();
let amount=parseFloat(loanAmountReq.value);
let tenure=parseInt(loanTenurePref.value);
let score=parseInt(creditScore.value);
let co=coApplicant.value;

let months=tenure*12;

let lowestEMI=Infinity;
let bestBank="";
let rows=[];

banks.forEach(bank=>{

let interest=bank[type];

let approval=score>=750?90:score>=650?70:score>=550?50:20;

if(co==="Yes") approval+=5;

let R=interest/12/100;

let EMI=(amount*R*Math.pow(1+R,months))/(Math.pow(1+R,months)-1);

if(EMI<lowestEMI){
lowestEMI=EMI;
bestBank=bank.name;
}

rows.push({name:bank.name,interest,EMI,approval});

});

let table="<h3>Recommended Bank: "+bestBank+"</h3>";

table+="<table><tr><th>Bank</th><th>Interest %</th><th>EMI</th><th>Approval</th></tr>";

rows.forEach(bank=>{
let highlight=bank.name===bestBank?"class='recommended'":"";
table+=`<tr ${highlight}><td>${bank.name}</td><td>${bank.interest}%</td><td>₹${bank.EMI.toFixed(0)}</td><td>${bank.approval}%</td></tr>`;
});

table+="</table>";

hideAll();
loanResultSection.classList.add("active");

loanResult.innerHTML=table;

let finalApproval=score>=750?90:score>=650?70:score>=550?50:20;

if(co==="Yes") finalApproval+=5;

eligibilityBar.style.width=finalApproval+"%";

eligibilityText.innerHTML="Approval Probability: "+finalApproval+"%";

}

function goDashboard(){
hideAll();
dashboardSection.classList.add("active");
}

function logout(){
hideAll();
loginSection.classList.add("active");
}

function showUPI(){
hideAll();
upiSection.classList.add("active");
}

function showConverter(){
hideAll();
converterSection.classList.add("active");
}

function showCalculator(){
hideAll();
calculatorSection.classList.add("active");
}

function showChatbot(){
hideAll();
chatbotSection.classList.add("active");
}

function showEMIGraph(){
hideAll();
emiGraphSection.classList.add("active");
}