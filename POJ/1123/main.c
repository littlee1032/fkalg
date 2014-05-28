#include <iostream>
#include <cmath>
#include <memory>
#define MAX_N 22
using namespace std;

int N, M;
int expV[MAX_N + 1];
double fCost[MAX_N + 1];

int cReq[MAX_N + 1][2];
double cAffor[MAX_N + 1];

int fNum, cNum, minFN, maxCN, bestState;
double minCost, maxCost, maxPI, maxPM, maxSales, maxExp; 

bool cServerd[MAX_N + 1];
bool cSAssit[MAX_N + 1];
void init()
{
    maxPI = maxPM = INT_MIN;
    minFN = INT_MAX;
    maxCN = INT_MIN;
}


int main()
{
    int i, j, k, caseN = 0, temp;
    for(i = 0; i <= MAX_N + 1; i++)
        expV[i] = pow(double(2), i);

    cin>>caseN;
    for(i = 1; i <= caseN; i++)
        {
            init();
            cin>>minCost>>maxCost>>fNum>>cNum;
            for(j = 0; j < fNum; j++)
                cin>>fCost[j];
            for(j = 0; j < cNum; j++)
                {
                    cin>>cReq[j][0];
                    int total = 0;
                    for(k = 1; k <= cReq[j][0]; k++)
                        {
                            cin>>temp;
                            total += expV[temp - 1];
                        }
                    cReq[j][1] = total;
                    cin>>cAffor[j];
                }
            int curState;
            double curPI, curPM;
            int curFN, curCN;
            double curSales, curCost;
            for(curState = 0; curState <= expV[fNum] - 1; curState++)
                {
                    if(curState == 57)
                        {
                            int a = 2;
                        }
                    curPI = curPM = curFN = curCN = curSales = curCost = 0;
                    for(j = 0; j < fNum; j++)
                        if(expV[j] & curState)
                            {
                                curFN++;
                                curCost += fCost[j];
                            }
                    if(!(curCost >= minCost && curCost <= maxCost))
                        continue;
                    for(j = 0; j < cNum; j++)
                        {
                            int newVal = curState | cReq[j][1];
                            if(newVal == curState)
                                {
                                    curCN++;
                                    curSales += cAffor[j]; 
                                    cSAssit[j] = true;
                                }
                            else
                                cSAssit[j] = false;
                        }
                    curPI = curSales / curCost;
                    int tt = curPI * 10000 + 5;
                    tt = tt / 10 * 10;
                    curPI = double(tt) / double(10000);
                    curPM = curSales - curCost;

                    bool update = false;
                    if(curPI > maxPI)
                        update = true;
                    else if(curPI == maxPI)
                        {
                            if(curPM > maxPM)
                                update = true;
                            else if(curPM == maxPM)
                                {
                                    if(curFN < minFN)
                                        update = true;
                                    else if(curFN == minFN)
                                        {
                                            if(curCN > maxCN)
                                                update = true;
                                        }
                                }
                        }
                    if(update)
                        {
                            bestState = curState;
                            maxPI = curPI;
                            maxPM = curPM;
                            minFN = curFN;
                            maxCN = curCN;
                            maxSales = curSales;
                            maxExp = curCost;
                            for(j = 0; j < cNum; j++)
                                cServerd[j] = cSAssit[j];
                        }
                }
            cout<<"Feature Set "<<i<<endl;
            printf("%.3f/n", maxPI);
            printf("%.0f/n", maxSales);
            printf("%.0f/n", maxExp);
        
            bool v = false;
            for(j = 0; j < fNum; j++)
                {
                    if(bestState & expV[j])
                        {
                            if(v)
                                cout<<" ";
                            else
                                v = true;
                            cout<<j + 1;
                        }
                }
            cout<<endl;
            v = false;
            for(j = 0; j < cNum; j++)
                {
                    if(cServerd[j])
                        {
                            if(v)
                                cout<<" ";
                            else
                                v = true;
                            cout<<j + 1;
                        }
                }
            cout<<endl;
        }
    return 0;
}

