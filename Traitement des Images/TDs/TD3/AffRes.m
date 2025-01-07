function AffRes(P,tb,aff)
if nargin==3
    figure(aff)
   hold on
end
for k=1:size(P,1)
   
   rectangle('position',[P(k,1)-tb/2 P(k,2)-tb/2 tb tb],...
          'edgecolor','g','linewidth',2);
end