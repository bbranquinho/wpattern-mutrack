require 'unirest'
#require 'open-uri'
require 'nokogiri'

response = Unirest.post "http://www.rastreamentocorreios.com.br/package/track",
                        headers:{ "Accept" => "text/plain" },
                        parameters:{ :_token => "r5qv2gxjeJdgz1cnCc9myoIe05bpeWpT7myekTm4", :package_code => "RI220874555CN" }

response.code # Status code
response.headers # Response headers
response.body # Parsed body
response.raw_body # Unparsed body

doc = Nokogiri::HTML::Document.parse(response.body)
vetor  = []
doc.xpath('//tbody/tr').each do |node|
     vetor.push(node.text.strip)
end

puts doc.search("div")[0].search("strong")[0].text

#comentando essa parte para otimizar o tratamento das strings, deixando a responsabilidade para a view
#vetor.each_index {|x| vetor[x] = vetor[x].to_s.split("\n") }

#puts vetor[1].inspect

#puts vetor.inspect
#puts vetor.size